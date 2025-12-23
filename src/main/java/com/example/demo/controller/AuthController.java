@PostMapping("/register")
public User register(@RequestBody RegisterRequest request) {

    User user = new User(
            request.getName(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword()), // ✅ encode here
            request.getRole()
    );

    return userService.registerUser(user);
}
