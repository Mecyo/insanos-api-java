package com.mecyo.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mecyo.spring.domain.model.JwtRequest;
import com.mecyo.spring.domain.model.JwtResponse;
import com.mecyo.spring.security.JwtTokenUtil;
import com.mecyo.spring.security.JwtUserDetailsService;
import com.mecyo.spring.security.UsuarioSistema;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UsuarioSistema userDetails = (UsuarioSistema) userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(userDetails, token));
	}

	@RequestMapping(value = "/token-validate", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> validarToken(@RequestBody String token) throws Exception {
		JwtResponse response = null;
		if (!jwtTokenUtil.isTokenExpired(token)) {
			String username = jwtTokenUtil.getUsernameFromToken(token);
			final UsuarioSistema userDetails = (UsuarioSistema) userDetailsService.loadUserByUsername(username);
			final String newToken = jwtTokenUtil.generateToken(userDetails);
			response = new JwtResponse(userDetails, newToken);
		}

		return ResponseEntity.ok(response);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}