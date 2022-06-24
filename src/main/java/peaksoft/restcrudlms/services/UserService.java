package peaksoft.restcrudlms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.restcrudlms.dto.jwtDto.RegisterRequest;
import peaksoft.restcrudlms.dto.jwtDto.RegisterResponse;
import peaksoft.restcrudlms.entities.User;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;
import peaksoft.restcrudlms.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse create(RegisterRequest request) {
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }

    private User mapToEntity(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    private RegisterResponse mapToResponse(User user) {
        if (user == null) {
            return null;
        }
        RegisterResponse response = new RegisterResponse();
        if (user.getId() != null) {
            response.setId(String.valueOf(user.getId()));
        }
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());

        return response;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ObjectNotFoundException("User with email: " + username + " not found!"));
    }
}
