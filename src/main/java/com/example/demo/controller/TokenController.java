// package com.example.demo.controller;

// import com.example.demo.entity.Token;
// import com.example.demo.service.TokenService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/tokens")
// @Tag(name = "Tokens")
// public class TokenController {

//     private final TokenService tokenService;

//     public TokenController(TokenService tokenService) {
//         this.tokenService = tokenService;
//     }

//     @PostMapping("/issue/{counterId}")
//     @Operation(summary = "Issue new token")
//     public Token issueToken(@PathVariable Long counterId) {
//         return tokenService.issueToken(counterId);
//     }

//     @PutMapping("/status/{tokenId}")
//     @Operation(summary = "Update token status")
//     public Token updateStatus(
//             @PathVariable Long tokenId,
//             @RequestParam String status) {
//         return tokenService.updateStatus(tokenId, status);
//     }

//     @GetMapping("/{tokenId}")
//     @Operation(summary = "Get token details")
//     public Token getToken(@PathVariable Long tokenId) {
//         return tokenService.getToken(tokenId);
//     }
// }


// package com.example.demo.controller;

// import com.example.demo.entity.Token;
// import com.example.demo.entity.TokenLog;
// import com.example.demo.service.TokenService;
// import com.example.demo.service.TokenLogService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/tokens")
// public class TokenController {
//     private final TokenService tokenService;
//     private final TokenLogService logService;

//     public TokenController(TokenService tokenService, TokenLogService logService) {
//         this.tokenService = tokenService;
//         this.logService = logService;
//     }

//     @PostMapping("/issue/{counterId}")
//     public ResponseEntity<Token> issueToken(@PathVariable Long counterId) {
//         Token token = tokenService.issueToken(counterId);
//         return ResponseEntity.ok(token);
//     }

//     @PutMapping("/{tokenId}/status")
//     public ResponseEntity<Token> updateStatus(@PathVariable Long tokenId, @RequestParam String status) {
//         Token token = tokenService.updateStatus(tokenId, status);
//         return ResponseEntity.ok(token);
//     }

//     @GetMapping("/{tokenId}")
//     public ResponseEntity<Token> getToken(@PathVariable Long tokenId) {
//         Token token = tokenService.getToken(tokenId);
//         return ResponseEntity.ok(token);
//     }

//     @GetMapping("/{tokenId}/logs")
//     public ResponseEntity<List<TokenLog>> getLogs(@PathVariable Long tokenId) {
//         List<TokenLog> logs = logService.getLogs(tokenId);
//         return ResponseEntity.ok(logs);
//     }

//     @PostMapping("/{tokenId}/logs")
//     public ResponseEntity<TokenLog> addLog(@PathVariable Long tokenId, @RequestParam String message) {
//         TokenLog log = logService.addLog(tokenId, message);
//         return ResponseEntity.ok(log);
//     }

//     @GetMapping("/counter/{counterId}")
//     public ResponseEntity<List<Token>> getTokensByCounter(@PathVariable Long counterId, @RequestParam(defaultValue = "WAITING") String status) {
//         List<Token> tokens = tokenService.getTokensByCounter(counterId, status);
//         return ResponseEntity.ok(tokens);
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.impl.TokenServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    private final TokenServiceImpl tokenService;
    
    public TokenController(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }
    
    @PostMapping("/issue/{counterId}")
    public Token issueToken(@PathVariable Long counterId) {
        return tokenService.issueToken(counterId);
    }
    
    @PutMapping("/status/{tokenId}")
    public Token updateStatus(@PathVariable Long tokenId, @RequestBody String status) {
        return tokenService.updateStatus(tokenId, status);
    }
    
    @GetMapping("/{tokenId}")
    public Token getToken(@PathVariable Long tokenId) {
        return tokenService.getToken(tokenId);
    }
}