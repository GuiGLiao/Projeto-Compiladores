package io.compiler.main;

import org.antlr.v4.runtime.CommonTokenStream;
//import org.antlr.v4.runtime.Token;

import java.io.File; // Add this import statement

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;

import io.compiler.core.IsiLanguageLexer;
import io.compiler.core.IsiLanguageParser;
import io.compiler.core.ast.Program;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLanguageLexer lexer;
			IsiLanguageParser parser;
			
			// crio o analisador léxico a partir da leitura de um arquivo
			lexer = new IsiLanguageLexer(CharStreams.fromFileName("IsiLanguage/teste1_semValorIni.isi"));
			
			// agora a partir do analisador lexico, obtenho um fluxo de tokens
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// crio o parser a partir do tokenStream
			parser = new IsiLanguageParser(tokenStream);
			
			// agora eu quero chamar do meu jeito
			System.out.println("UFABC Compiler");
			parser.programa();
			System.out.println("Compilation Successfully - Good Job");
			parser.exibirVar();

			parser.Warnings();

			Program program = parser.getProgram();

			try {
				File f = new File(program.getName()+".java");
				FileWriter fw = new FileWriter(f);
				PrintWriter pr = new PrintWriter(fw);
				pr.println(program.generateTarget());
				pr.close();
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			System.out.println(program.generateTarget());
		}
		catch(Exception ex) {
			System.err.println("Error: "+ex.getMessage());
			//ex.printStackTrace();
		}
	}
}
