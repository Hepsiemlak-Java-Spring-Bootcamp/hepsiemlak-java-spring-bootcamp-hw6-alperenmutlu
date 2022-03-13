package emlakburada.service;

import emlakburada.model.User;
import emlakburada.model.dto.reponse.UserResponse;
import emlakburada.model.dto.request.UserRequest;

public class UserBaseService {

    protected User convertToUserEntity(UserRequest userRequest){
            User user = new User();
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhoto(userRequest.getPhoto());
            user.setBio(userRequest.getBio());
            user.setUserType(userRequest.getUserType());
            return user;
    }

    protected UserResponse convertToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoto(user.getPhoto());
        userResponse.setBio(user.getBio());
        userResponse.setUserType(user.getUserType());
        return userResponse;
    }


}
