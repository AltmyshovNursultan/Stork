package delivery.stork.secutiry;

import delivery.stork.model.entity.User;
import delivery.stork.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("No such user found with "+username +"email!"));
        return UserDetailsImpl.build(user);
    }
}
