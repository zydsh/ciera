package io.ciera.template.rsl.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class RSLParseUtil {
    
    private static final String OUT_FILE = "gen/code_generation/d.xtuml";
    private static PrintStream out;

    public static void main( String[] args ) {
        try {
            out = new PrintStream( new File(OUT_FILE) );
            out.println( "-- RSL data" );
            if ( args.length > 0 ) {
                File template_dir = new File( args[0] );
                processFile( template_dir, "" );
            }
            out.close();
        } catch ( IOException e ) { /* do nothing */ }
    }
    
    private static void processFile( File f, String path ) {
        if ( null != f && f.exists() && null != path ) {
            System.err.printf( "--   Processing file: %s\n", f.getName() );
            if ( f.isDirectory() ) {
                for ( File contained_file : f.listFiles() ) processFile( contained_file, "".equals( path ) ? f.getName() : path + "/" + f.getName() );
            }
            else {
                try {
                    RSLLexer lexer = new RSLLexer( CharStreams.fromStream( new FileInputStream( path + "/" + f.getName() ) ) );
                    /* lexer debug
                    for ( Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer.nextToken() ) {
                        System.err.println( lexer.getVocabulary().getSymbolicName(token.getType()));
                    }
                    */
                    CommonTokenStream tokens = new CommonTokenStream( lexer );
                    RSLParser parser = new RSLParser( tokens );
                    RSLParser.BodyContext ctx = parser.body();
                    ParseTreeWalker walker = new ParseTreeWalker();
                    String fn = path + "/" + f.getName();
                    fn = fn.substring( fn.indexOf( '/' ) + 1 );
                    XtumlRSLListener listener = new XtumlRSLListener( fn, out );
                    walker.walk( listener, ctx );
                } catch ( IOException e ) { /* do nothing */ }
            }
        }
    }

}