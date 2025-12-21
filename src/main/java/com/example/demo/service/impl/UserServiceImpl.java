@Override
public AuthResponse register(RegisterRequest request) {

    // create user entity and save
    // assuming you already have User entity + repository

    // Example basic logic (adjust to your DB fields):
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setEmail(request.getEmail());
    user.setRole(request.getRole());

    userRepository.save(user);

    // generate token
    String token = jwtService.generateToken(user.getUsername());

    return new AuthResponse(token);
}

@Override
public AuthResponse login(AuthRequest request) {

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            )
    );

    String token = jwtService.generateToken(request.getUsername());

    return new AuthResponse(token);
}
