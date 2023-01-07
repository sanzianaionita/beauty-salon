package com.example.beautysalon.service;

import com.example.beautysalon.customExceptions.CustomException;
import com.example.beautysalon.dto.BeautyUserDTO;
import com.example.beautysalon.dto.LoggedApplicationUserDTO;
import com.example.beautysalon.dto.RegisterModelDTO;
import com.example.beautysalon.mappers.BeautyUserMapper;
import com.example.beautysalon.model.BeautyUser;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.enums.Authority;
import com.example.beautysalon.repository.BeautyUserRepository;
import com.example.beautysalon.repository.ClientRepository;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.security.config.ConfigClass;
import com.example.beautysalon.security.jwt.JwtTokenUtil;
import com.example.beautysalon.security.service.SecurityUserService;
import com.example.beautysalon.security.util.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BeautyUserService {

    private final BeautyUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityUserService securityUserService;
    private final ConfigClass configClass;
    private final BeautyUserMapper mapper;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    public BeautyUserService(BeautyUserRepository userRepository,
                             AuthenticationManager authenticationManager,
                             JwtTokenUtil jwtTokenUtil,
                             SecurityUserService securityUserService,
                             ConfigClass configClass,
                             BeautyUserMapper mapper,
                             EmployeeRepository employeeRepository,
                             ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.securityUserService = securityUserService;
        this.configClass = configClass;
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    public LoggedApplicationUserDTO login(String email, String password) throws Exception {
        Optional<BeautyUser> byUserName = userRepository.findByEmail(email);

        if (byUserName.isEmpty()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        authenticate(email, password);
        UserDetails userDetails = securityUserService.loadUserByUsername(email);
        String token = jwtTokenUtil.generateToken(userDetails ,byUserName.get().getId());

        SecurityUtils.setJwtToken(token);

        BeautyUserDTO applicationUserDTO = mapper.userToUserDTO(byUserName.get());
        LoggedApplicationUserDTO loggedApplicationUserDTO = new LoggedApplicationUserDTO(applicationUserDTO);

        loggedApplicationUserDTO.setToken(token);

        return loggedApplicationUserDTO;

    }

    public BeautyUserDTO register(RegisterModelDTO applicationUserDTO) {
        Optional<BeautyUser> existingUser = userRepository.findByEmail(applicationUserDTO.getUser().getEmail());

        if (existingUser.isPresent()) {
            throw new CustomException("User already exists!", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        BeautyUserDTO userDTO = applicationUserDTO.getUser();

        userDTO.setPassword(configClass.passwordEncoder().encode(userDTO.getPassword()));
        BeautyUser savedUser = userRepository.save(mapper.userDtoToUser(userDTO));

        if (savedUser.getAuthority().equals(Authority.EMPLOYEE)) {
            Employee employee = new Employee();
            employee.setId(UUID.randomUUID());
            employee.setUser(savedUser);
            employee.setWage(2500);
            employee.setEmploymentDate(LocalDate.now());
            employee.setFirstName(applicationUserDTO.getFirstName());
            employee.setLastName(applicationUserDTO.getLastName());

            employeeRepository.save(employee);
        } else if (savedUser.getAuthority().equals(Authority.CLIENT)) {
            Client client = new Client();
            client.setId(UUID.randomUUID());
            client.setUser(savedUser);
            client.setLastName(applicationUserDTO.getLastName());
            client.setFirstName(applicationUserDTO.getFirstName());
            client.setPhoneNumber(applicationUserDTO.getPhoneNumber());

            clientRepository.save(client);
        }

        return mapper.userToUserDTO(savedUser);
    }

    public BeautyUserDTO getUserDetails(UUID userId) {
        if (SecurityUtils.getCurrentUser() != null && SecurityUtils.getUserId().equals(userId)) {
            Optional<BeautyUser> byId = userRepository.findById(userId);

            if (byId.isEmpty()) {
                throw new CustomException("User does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
            }
            return mapper.userToUserDTO(byId.get());
        } else {
            throw new CustomException("Error!", HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private void authenticate(String userName, String password) throws Exception {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new CustomException("Bad email or password", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }
    }
}
