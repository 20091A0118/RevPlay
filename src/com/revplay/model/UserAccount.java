package com.revplay.model;

public class UserAccount {

    private int userId;
    private String fullName;
    private String email;
    private String passwordHash;
    private String securityQuestion;
    private String securityAnswer;
    private String status;

    public UserAccount() {}

    public UserAccount(int userId, String fullName, String email,
                       String passwordHash, String securityQuestion,
                       String securityAnswer, String status) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.status = status;
    }

    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getSecurityQuestion() { return securityQuestion; }
    public String getSecurityAnswer() { return securityAnswer; }
    public String getStatus() { return status; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }
    public void setStatus(String status) { this.status = status; }
}