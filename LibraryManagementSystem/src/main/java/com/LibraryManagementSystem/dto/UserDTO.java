package com.LibraryManagementSystem.dto;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;

    public UserDTO(int i, String johnDoe, String mail, String johndoe, String pass123, Object o) {
    }

    public UserDTO() {

    }

}

