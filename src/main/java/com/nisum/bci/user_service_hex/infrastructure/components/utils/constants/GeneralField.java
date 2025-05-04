package com.nisum.bci.user_service_hex.infrastructure.components.utils.constants;

public class GeneralField {
    private GeneralField(){}
    public static final String LOCAL_DATE_TIME_FORMAT_PATTERN ="dd/MM/yyy h:mm:ss a";
    public static final String NO_USER_CREATED = "User creation error";
    public static final String USER_EXISTS = "The user already exists";
    public static final String PHONE_NUMBER_EXISTS = "The phone number has already been registered, please try a new one";
    public static final String USER_SUMMARY_OPERARION = "Register a new user";
    public static final String USER_SUMMARY_DESC = "Register a new user, ensuring that the email address does not exist, the password matches the pattern, and the phone numbers are unique.";
    public static final String USER_SUCCESSFULL = "User successfully registered";
    public static final String GET_USER_SUMMARY_OPERARION = "Get User by ID";
    public static final String GET_USER_DESC_OPERARION = "Retrieves data for the user specified by their ID. Authentication is required.";
    public static final String GET_USER_SUCC = "User found and returned";
    public static final String GET_USER_NO_AUTH = " Not authenticated";
    public static final String GET_USER_NOTFOUND = "User not found";
    public static final String GET_USER_UUID = "ID for User (UUID)";







}
