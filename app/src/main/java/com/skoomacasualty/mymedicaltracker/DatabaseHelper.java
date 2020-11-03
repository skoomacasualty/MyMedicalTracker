package com.skoomacasualty.mymedicaltracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    // Primary Keys for each table are rowid

    // Database name and version
    private final static String DB_NAME = "medicalFolder";
    private final static int VERSION = 1;

    // Doctors Table
    private static final String TABLE_DOCTORS = "doctors";
    // Doctors Columns
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";

    // Clinics Table
    private static final String TABLE_CLINICS = "clinics";
    // Clinics Columns
    private static final String KEY_CLINIC_NAME = "clinicName";
    private static final String KEY_NETWORK = "network";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_HOURS = "hours";
    private static final String KEY_CLINIC_PHONE = "phone";
    private static final String KEY_FAX = "fax";
    private static final String KEY_WEBSITE = "website";

    // Prescriptions Table
    private static final String TABLE_PRESCRIPTIONS = "prescriptions";
    // Prescriptions Columns
    private static final String KEY_BRAND_NAME = "brandName";
    private static final String KEY_GENERIC_NAME = "genericName";
    private static final String KEY_PRESCRIBED_DATE = "date";
    private static final String KEY_DOSAGE = "dosage";
    private static final String KEY_SCHEDULE = "schedule";
    private static final String KEY_SIDE_EFFECTS = "sideEffects";

    // Appointments Table
    private static final String TABLE_APPOINTMENTS = "appointments";
    // Appointments Columns
    private static final String KEY_APPOINTMENT_DATE = "date";
    private static final String KEY_REASON = "reason";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_OUTCOME = "outcome";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_TODO = "toDo";

    // Tests Table
    private static final String TABLE_TESTS = "tests";
    // Tests Columns
    private static final String KEY_TYPE = "type";
    private static final String KEY_BIOMARKERS = "biomarkers";
    private static final String KEY_RESULT = "result";
    private static final String KEY_CHANGE = "change";

    // Diagnoses Table
    private static final String TABLE_DIAGNOSES = "diagnoses";
    // Diagnoses Columns
    private static final String KEY_DIAGNOSIS_NAME = "name";
    private static final String KEY_TREATMENT = "treatment";

    // Symptoms Table
    private static final String TABLE_SYMPTOMS = "symptoms";
    // Symptoms Columns
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_NOTICE_DATE = "dateNoticed";
    private static final String KEY_FREQUENCY = "frequency";
    private static final String KEY_SYMPTOM_CALENDAR = "calendar";

    // Conversion Tables
    private static final String TABLE_DIAGNOSIS_PRESCRIPTION = "diagnosisPrescription";
    private static final String TABLE_DIAGNOSIS_SYMPTOM = "diagnosisSymptom";
    private static final String TABLE_SYMPTOM_PRESCRIPTION = "symptomPrescription";
    private static final String TABLE_APPOINTMENT_SYMPTOM = "appointmentSymptom";

    // Foreign Keys
    private static final String KEY_CLINIC_ID = "clinicID";
    private static final String KEY_DOCTOR_ID = "doctorID";
    private static final String KEY_APPOINTMENT_ID = "appointmentID";
    private static final String KEY_PRESCRIPTION_ID = "prescriptionID";
    private static final String KEY_DIAGNOSIS_ID = "diagnosisID";
    private static final String KEY_SYMPTOM_ID = "symptomID";

    /*****************************
     * DatabaseHelper Constructor
     *****************************/
    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /**************
     * getInstance
     **************/
    // Check for instance of database, initialize if not present
    public static synchronized DatabaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    /******************
     * SQLite onCreate
     ******************/
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Doctors Table CREATE String
        String CREATE_DOCTORS_TABLE = "CREATE TABLE " + TABLE_DOCTORS + " (" +
                KEY_FIRST_NAME + " TEXT, " +
                KEY_LAST_NAME + " TEXT, " +
                KEY_PHONE + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_CLINIC_ID + " INT);";

        // Clinics Table CREATE String
        String CREATE_CLINICS_TABLE = "CREATE TABLE " + TABLE_CLINICS + " (" +
                KEY_CLINIC_NAME + " TEXT, " +
                KEY_NETWORK + " TEXT, " +
                KEY_ADDRESS + " TEXT, " +
                KEY_HOURS + " TEXT, " +
                KEY_CLINIC_PHONE + " TEXT, " +
                KEY_FAX + " TEXT, " +
                KEY_WEBSITE + " TEXT);";

        // Prescriptions Table CREATE String
        String CREATE_PRESCRIPTIONS_TABLE = "CREATE TABLE " + TABLE_PRESCRIPTIONS + " (" +
                KEY_BRAND_NAME + " TEXT, " +
                KEY_GENERIC_NAME + " TEXT, " +
                KEY_PRESCRIBED_DATE + " TEXT, " +
                KEY_DOSAGE + " TEXT, " +
                KEY_SCHEDULE + " TEXT, " +
                KEY_SIDE_EFFECTS + " TEXT, " +
                KEY_DOCTOR_ID + " INT);";

        // Appointments Table CREATE String
        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS + " (" +
                KEY_APPOINTMENT_DATE + " TEXT, " +
                KEY_REASON + " TEXT, " +
                KEY_QUESTION + " TEXT, " +
                KEY_OUTCOME + " TEXT, " +
                KEY_START_TIME + " TEXT, " +
                KEY_END_TIME + " TEXT, " +
                KEY_TODO + " TEXT, " +
                KEY_DOCTOR_ID + " INT, " +
                KEY_CLINIC_ID + " INT);";

        // Tests Table CREATE String
        String CREATE_TESTS_TABLE = "CREATE TABLE " + TABLE_TESTS + " (" +
                KEY_TYPE + " TEXT, " +
                KEY_BIOMARKERS + " TEXT, " +
                KEY_RESULT + " TEXT, " +
                KEY_CHANGE + " TEXT, " +
                KEY_APPOINTMENT_ID + " INT);";

        // Diagnosis Table CREATE String
        String CREATE_DIAGNOSES_TABLE = "CREATE TABLE " + TABLE_DIAGNOSES + " (" +
                KEY_DIAGNOSIS_NAME + " TEXT, " +
                KEY_DIAGNOSIS_NAME + " TEXT, " +
                KEY_TREATMENT + " TEXT, " +
                KEY_APPOINTMENT_ID + " INT);";

        // Symptoms Table CREATE String
        String CREATE_SYMPTOMS_TABLE = "CREATE TABLE " + TABLE_SYMPTOMS + " (" +
                KEY_DESCRIPTION + " TEXT, " +
                KEY_NOTICE_DATE + " TEXT, " +
                KEY_FREQUENCY + " TEXT, " +
                KEY_SYMPTOM_CALENDAR + " TEXT);";

        // DiagnosisPrescription Table CREATE String
        String CREATE_DIAGNOSIS_PRESCRIPTION_TABLE = "CREATE TABLE " + TABLE_DIAGNOSIS_PRESCRIPTION + " (" +
                KEY_PRESCRIPTION_ID + " INT, " +
                KEY_DIAGNOSIS_ID + " INT);";

        // DiagnosisSymptom Table CREATE String
        String CREATE_DIAGNOSIS_SYMPTOM_TABLE = "CREATE TABLE " + TABLE_DIAGNOSIS_SYMPTOM + " (" +
                KEY_SYMPTOM_ID + " INT, " +
                KEY_DIAGNOSIS_ID + "INT);";

        // SymptomPrescription Table CREATE String
        String CREATE_SYMPTOM_PRESCRIPTION_TABLE = "CREATE TABLE " + TABLE_SYMPTOM_PRESCRIPTION + " (" +
                KEY_SYMPTOM_ID + " INT, " +
                KEY_PRESCRIPTION_ID + " INT);";

        // AppointmentSymptom Table CREATE String
        String CREATE_APPOINTMENT_SYMPTOM_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT_SYMPTOM + " (" +
                KEY_SYMPTOM_ID + " INT, " +
                KEY_APPOINTMENT_ID + " INT);";

        // Execute SQL CREATE Statements
        db.execSQL(CREATE_DOCTORS_TABLE);
        db.execSQL(CREATE_CLINICS_TABLE);
        db.execSQL(CREATE_PRESCRIPTIONS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
        db.execSQL(CREATE_TESTS_TABLE);
        db.execSQL(CREATE_DIAGNOSES_TABLE);
        db.execSQL(CREATE_SYMPTOMS_TABLE);
        db.execSQL(CREATE_DIAGNOSIS_PRESCRIPTION_TABLE);
        db.execSQL(CREATE_DIAGNOSIS_SYMPTOM_TABLE);
        db.execSQL(CREATE_SYMPTOM_PRESCRIPTION_TABLE);
        db.execSQL(CREATE_APPOINTMENT_SYMPTOM_TABLE);
    }
}
