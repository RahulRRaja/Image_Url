package com.sample.Image.url.service;

import com.sample.Image.url.model.User;
import com.sample.Image.url.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final UserRepository userRepository;

    public FileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(MultipartFile file) throws IOException {
        User user = new User();
        user.setName(StringUtils.cleanPath(file.getOriginalFilename()));
       String UploadRandomName = String.valueOf(Math.random());
       String FileName = file.getOriginalFilename();
       user.setRandomName(StringUtils.cleanPath(UploadRandomName+"_" + FileName));
        user.setContentType(file.getContentType());
        user.setData(file.getBytes());
        user.setSize(file.getSize());

        userRepository.save(user);
    }

    public Optional<User> getFile(String id) {
        return userRepository.findById(id);
    }

    public List<User> getAllFiles() {
        return userRepository.findAll();
    }
}
