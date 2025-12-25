// package com.example.demo.service;

// import com.example.demo.entity.User;

// public interface UserService {
//     User registerUser(User user);
//     User findByEmail(String email);
// }

// package com.example.demo.service;

// import com.example.demo.entity.User;

// public interface UserService {
//     User register(User user);
//     User findByEmail(String email);
// }
// package com.example.demo.service;

// import com.example.demo.entity.User;

// public interface UserService {
//     User register(User user);
//     User findByEmail(String email);
// }
// package com.example.demo.service;

// import com.example.demo.entity.QueuePosition;

// public interface QueueService {
//     QueuePosition updateQueuePosition(Long tokenId, int position);
// }

package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);
}
