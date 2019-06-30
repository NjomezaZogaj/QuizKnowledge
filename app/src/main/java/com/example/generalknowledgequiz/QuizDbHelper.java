package com.example.generalknowledgequiz;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.generalknowledgequiz.QuizContract.*;
import android.support.annotation.Nullable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="GenaralKnowledgeQuiz.db";
    private static final int DATEBASE_VERSION=1;

    private  static QuizDbHelper instance;

    private SQLiteDatabase db;



    private QuizDbHelper( Context context) {
        super(context, DATABASE_NAME,null,DATEBASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if(instance==null){
            instance=new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_CATEGORIES_TABLE= "CREATE TABLE " +
                CategoriesTable.TABLE_NAME+"( "+
                CategoriesTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,  "+
                CategoriesTable.COLUMN_NAME+" TEXT "+
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE=" CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
                db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
                db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
                fillCategoriesTable();
                fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Geography");
        addCategory(c2);
        Category c3 = new Category("Music");
        addCategory(c3);
        Category c4 = new Category("Science");
        addCategory(c4);
    }
    private void addCategory(Category category){
        ContentValues cv=new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME,null,cv);

    }

    private void fillQuestionsTable(){
        Question q1 = new Question("What does HTML mean?",
                "Hypertext Markup Language", "Home Tool Markup Language", "Hyperlinks and Text Markups"
                , 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q1);
        Question q2 = new Question("Which SQL statement is used to extract data from a database?",
                "OPEN", "SELECT", "EXTRACT", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q2);
        Question q3 = new Question("Which sign does jQuery use as a shortcut for jQuery?",
                "The ? sign", "The % sign", "The $ sign", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q3);
        Question q4 = new Question("Choose the correct HTML element for the largest heading:",
                "<heading>", "<h1>", "<h6>", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q4);
        Question q5 = new Question("Where in an HTML document is the correct place to refer to an external style sheet?",
                "In the <body> section", "At the end of the document", "In the <head> section", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q5);
        Question q6 = new Question("Which SQL statement is used to update data in a database?",
                "UPDATE", "SAVE ", "MODIFY", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q6);
        Question q7 = new Question("With SQL, how do you select a column named FirstName from a table named Persons?",
                "SELECT FirstName from Persons", "EXTRACT from Persons", "SELECT Persons.FirstName", 1,
                Question.DIFFICULTY_HARD, 1);
        addQuestion(q7);
        Question q8 = new Question("Look at the following selector: $(div). What does it select?",
                "The second div", "All div elements", "The first div", 2,
                Question.DIFFICULTY_HARD, 1);
        addQuestion(q8);
        Question q9 = new Question("Which river flows through Paris?" ,
                "River Seine", "River Marne", "River Bierne"
                , 1, Question.DIFFICULTY_EASY, 2);
        addQuestion(q9);
        Question q10 = new Question("Which is the capital city of United Kingdom?" ,
                "Liverpool", "London", "Glasgow"
                , 2, Question.DIFFICULTY_EASY, 2);
        addQuestion(q10);
        Question q11 = new Question(" Of which country is Sofia the capital?" ,
                "Turkey", "Bulgaria", "Albania", 2, Question.DIFFICULTY_EASY, 2);
        addQuestion(q11);
        Question q12 = new Question(" What is the highest active volcano in Europe?" ,
                "Mount Halens", "Mount Etna", "Mount Loa", 2, Question.DIFFICULTY_MEDIUM, 2);
        addQuestion(q12);
        Question q13 = new Question(" In which US state is the Harvard University?" ,
                "LA", "Washington D.C", "Massachusetts", 3, Question.DIFFICULTY_MEDIUM, 2);
        addQuestion(q13);
        Question q14 = new Question("What’s the world’s biggest port?" ,
                "Port of Shanghai", "Port of Ningbo", "Port of Tianjin", 1, Question.DIFFICULTY_MEDIUM, 2);
        addQuestion(q14);
        Question q15 = new Question("Apart from Dutch and French, what is the other official language of Belgium?" ,
                "Italian", "French", "German", 3, Question.DIFFICULTY_HARD, 2);
        addQuestion(q15);
        Question q16 = new Question("In which European country is the cathedral town of Teruel?" ,
                "Greece", "Spain", "Italy", 2, Question.DIFFICULTY_HARD, 2);
        addQuestion(q16);
        Question q17 = new Question("Name the only country through which both the Equator and the Tropic of Capricorn pass." ,
                "Turkey", "Brazil", "Mexico", 2, Question.DIFFICULTY_HARD, 2);
        addQuestion(q17);
        Question q18 = new Question("What instrument has black and white keys?" ,
                "Guitar", "Viola", "Piano", 3, Question.DIFFICULTY_EASY, 3);
        addQuestion(q18);
        Question q19 = new Question("Which band sang Hotel California?" ,
                "Pink Floyd", "The Eagles", "The Beatles", 2, Question.DIFFICULTY_EASY, 3);
        addQuestion(q19);
        Question q20 = new Question("Who sang the song Whenever,Wherewer?" ,
                "Shakira", "Beyonce", "Jennifer Lopez", 1, Question.DIFFICULTY_EASY, 3);
        addQuestion(q20);
        Question q21 = new Question("When did Jazz music first appear?" ,
                "1895", "1990", "1885", 1, Question.DIFFICULTY_MEDIUM, 3);
        addQuestion(q21);
        Question q22 = new Question("How many members did the band Pink Floyd consist of?" ,
                "4", "2", "5", 3, Question.DIFFICULTY_MEDIUM, 3);
        addQuestion(q22);
        Question q23 = new Question("The Sydney Opera House is in which country?" ,
                "Australia", "Germany", "Austria", 1, Question.DIFFICULTY_MEDIUM, 3);
        addQuestion(q23);
        Question q24 = new Question("Which is lead singer of the rock band  Aeronsmith?" ,
                "Syd Barrett", "Steven Tyler", "John Lennon", 2, Question.DIFFICULTY_HARD, 3);
        addQuestion(q24);
        Question q25 = new Question("Where was Elton John born?" ,
                "UK", "Germany", "USA", 1, Question.DIFFICULTY_HARD, 3);
        addQuestion(q25);
        Question q26 = new Question("Where does bossa nova dance music originate?" ,
                "New Zeleand", "Mexico", "Brasil", 3, Question.DIFFICULTY_HARD, 3);
        addQuestion(q26);
        Question q27 = new Question("How many grams are there in a kilogram?" ,
                "10", "1000", "100", 2, Question.DIFFICULTY_EASY, 4);
        addQuestion(q27);
        Question q28 = new Question("Where was A.Einstein born?" ,
                "Germany", "UK", "Austria", 1, Question.DIFFICULTY_EASY, 4);
        addQuestion(q28);
        Question q29 = new Question("What colour are emeralds?" ,
                "Red", "White", "Green", 3, Question.DIFFICULTY_EASY, 4);
        addQuestion(q29);
        Question q30 = new Question("What number is the metric system based on?" ,
                "1000", "10", "100", 2, Question.DIFFICULTY_MEDIUM, 4);
        addQuestion(q30);
        Question q31 = new Question("What does a palaeontologist study?" ,
                "Fossils", "Insects", "Animals", 1, Question.DIFFICULTY_MEDIUM, 4);
        addQuestion(q31);
        Question q32 = new Question("What is the formula of calculating speed?" ,
                "Distance-Time", "Distance/Time", "Distance*Time", 2, Question.DIFFICULTY_MEDIUM, 4);
        addQuestion(q32);
        Question q33 = new Question("What temperature Fahrenheit does water boil at(sea level)?" ,
                "200", "326", "212", 3, Question.DIFFICULTY_HARD, 4);
        addQuestion(q33);
        Question q34 = new Question("What is the scientific study of fungi called?" ,
                "Heredity", "Epidemiology", "Mycology", 3, Question.DIFFICULTY_HARD, 4);
        addQuestion(q34);
        Question q35 = new Question("What are te materials that do not conduct electricty called?" ,
                "Insulators", "Conductors", "Resistors", 1, Question.DIFFICULTY_HARD, 4);
        addQuestion(q35);


    }
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }
    public List<Category> getAllcategories(){
        List<Category> categoryList= new ArrayList<>();
        db = getReadableDatabase();
        Cursor c=db.rawQuery(" SELECT * FROM "+CategoriesTable.TABLE_NAME,null);
        if (c.moveToFirst()){
            do{
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }
    public ArrayList<Question>getAllQuestions(){
        ArrayList<Question> questionList=new ArrayList<>();
        db=getReadableDatabase();
        Cursor c =db.rawQuery(" SELECT * FROM " + QuestionTable.TABLE_NAME,null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while(c.moveToNext());

        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(c.moveToFirst()){
            do{
                Question question= new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);

            }while(c.moveToNext());

        }
        c.close();
        return questionList;
    }
}
