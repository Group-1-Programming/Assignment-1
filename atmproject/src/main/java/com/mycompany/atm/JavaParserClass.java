package com.mycompany.atm;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.*;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class JavaParserClass {
    public static void main(String[] args) {
        try {
            JavaParser javaParser = new JavaParser();
            Path currentRelativePath = Paths.get("");
            String sep="//";
            String s =currentRelativePath.toAbsolutePath().toString()+ sep+"src.main.java".replaceAll("\\.", sep)+sep
                     +new JavaParserClass().getClass().getPackageName().replaceAll("\\.", sep)+sep;
            System.out.println("Current absolute path is: " + s);
           
            ParseResult<CompilationUnit> parseResult = javaParser
                    .parse(new FileInputStream(s + "Atmproject.java" ));
            CompilationUnit compilationUnit = parseResult.getResult().orElse(null);

            Optional<ClassOrInterfaceDeclaration> mainClass = compilationUnit.getClassByName("App");
            compilationUnit.findAll(FieldDeclaration.class).stream().filter(f -> f.isPublic() && !f.isStatic()).forEach(
                    f -> System.out.println("Check field at line " + f.getRange().map(r -> r.begin.line).orElse(-1)));

            YamlPrinter printer = new YamlPrinter(true);
            try{
            File obj = new File(s + "ast.yml");
            if (obj.createNewFile()) {
                System.out.println("File created: " + obj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(s+"ast.yml");
           myWriter.write(printer.output(compilationUnit)); 
            
           myWriter.close(); 
        }catch(IOException e){

        }
         
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
   
}
