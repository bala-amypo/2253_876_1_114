@RestController
@RequestMapping("/tokens")
@Tag(name = "Token Controller")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/issue/{counterId}")
    public ResponseEntity<Token> issueToken(@PathVariable Long counterId) {
        return ResponseEntity.ok(tokenService.issueToken(counterId));
    }

    @PutMapping("/status/{tokenId}")
    public ResponseEntity<Token> updateStatus(@PathVariable Long tokenId,
                                              @RequestParam String status) {
        return ResponseEntity.ok(tokenService.updateStatus(tokenId, status));
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<Token> getToken(@PathVariable Long tokenId) {
        return ResponseEntity.ok(tokenService.getToken(tokenId));
    }
}
