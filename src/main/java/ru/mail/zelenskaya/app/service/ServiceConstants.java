package ru.mail.zelenskaya.app.service;

public class ServiceConstants {
    private ServiceConstants() {
    }

    public static final int MIN_YEAR_STUDENT = 16;
    public static final int MIN_SIZE = 2;
    public static final int MAX_SIZE = 100;
    public static final String MESSAGE_NOT_BLANK_SURNAME = "The field 'surname' cannot be empty";
    public static final String MESSAGE_NOT_BLANK_NAME = "The field 'name'  cannot be empty";
    public static final String MESSAGE_NOT_BLANC_PATRONYMIC = "The field 'patronymic' cannot be empty";
    public static final String MESSAGE_SIZE_SURNAME = "The length of the surname can be from 2 to 100 characters";
    public static final String MESSAGE_SIZE_NAME = "The length of the name can be from 2 to 100 characters";
    public static final String MESSAGE_SIZE_PATRONYMIC = "The length of the patronymic can be from 2 to 100 characters";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
}
