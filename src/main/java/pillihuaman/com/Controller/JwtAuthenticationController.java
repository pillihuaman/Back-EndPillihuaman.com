package pillihuaman.com.Controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pillihuaman.com.JwtTokenUtil;
import pillihuaman.com.JwtUserDetailsService;
import pillihuaman.com.Help.MaestrosUtilidades;
import pillihuaman.com.RequestResponse.AuthenticationResponse;
import pillihuaman.com.RequestResponse.JwtRequest;
import pillihuaman.com.RequestResponse.JwtResponse;
import pillihuaman.com.security.PasswordUtils;


@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	  //@SuppressWarnings("unused")
	 //  @Autowired
	// private IUserService userService;
	  
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse auth= new AuthenticationResponse();
		auth.setToken(token);	
		auth.setId(1);
		//return ResponseEntity.ok().headers(responseHeaders).body(user);
		return ResponseEntity.ok(auth);
	}

	@SuppressWarnings("null")
	private void authenticate(String username, String password) throws Exception {
		//UsersResponse userResponse = new UsersResponse();
		boolean valida=false;
		try {
		
			/*Users user = new Users();
			user.setUsername(username);
			user.setPassword(password);
			user.setMail(username);
			userResponse= userService.UserSelByMail(user);
			 if(userResponse!=null   )
			 { 
				 if(!MaestrosUtilidades.isEmpty(userResponse.getUsers())) {
					 if(!MaestrosUtilidades.isEmpty(userResponse.getUsers().getPassword())||! MaestrosUtilidades.isEmpty(userResponse.getUsers().getSalPassword())||! MaestrosUtilidades.isEmpty(password))
					 {
			
							 valida=PasswordUtils.verifyUserPassword(password, userResponse.getUsers().getPassword(), userResponse.getUsers().getSalPassword());
							 if(valida==false)
							 {
								throw new UsernameNotFoundException("Users not found with username: " + username); 
							 }
					 } else
					 {
						 throw new UsernameNotFoundException("Users not found with username: " + username); 
					 }
				 } else
				 {
					 throw new UsernameNotFoundException("Users not found with username: " + username); 
				 }
			 }
			 else
			 {
				 throw new UsernameNotFoundException("Users not found with username: " + username); 
			 }*/
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}