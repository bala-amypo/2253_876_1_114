// package com.example.demo.service;

// import com.example.demo.entity.TokenLog;
// import java.util.List;

// public interface TokenLogService {
//     TokenLog addLog(Long tokenId, String message);
//     List<TokenLog> getLogs(Long tokenId);
// }


// package com.example.demo.service;

// import com.example.demo.entity.TokenLog;
// import java.util.List;

// public interface TokenLogService {
//     TokenLog addLog(Long tokenId, String message);
//     List<TokenLog> getLogs(Long tokenId);
// }

package com.example.demo.service;

import com.example.demo.entity.TokenLog;

public interface TokenLogService {
    TokenLog addLog(Long tokenId, String message);
}
