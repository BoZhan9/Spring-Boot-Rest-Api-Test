package com.example.restapitest.pojo.dto;

import com.example.restapitest.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class UserResponseDTO {
    @JsonProperty("userList")
    private List<UserDTO> userList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDTO {
        private String firstName;

        private String lastName;

        private String middleName;

        private String dob;

        public UserDTO(User user){
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.middleName = user.getMiddleName();
            this.dob = user.getDob();
        }



    }
}
