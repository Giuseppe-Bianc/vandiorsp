package org.dersbian.vandiorsp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LexerTest {

    /*TEST_CASE("default constructed token","[token]") {
    const vnd::Token token {};
        REQUIRE(token.getType() == vnd::TokenType::UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getFileName() == filename4);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
    }

    TEST_CASE("default constructed token toString","[token]") {
    const vnd::Token token {
        }
        ;
        REQUIRE(token.getType() == vnd::TokenType::UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getFileName() == filename4);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQUIRE(token.to_string() == "(type: UNKNOWN, value: '', source location:(file:unknown, line:0, column:0))");
    }

    TEST_CASE("default constructed token format","[token]") {
        vnd::Token token {
        }
        ;
        REQUIRE(token.getType() == vnd::TokenType::UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getFileName() == filename4);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQ_FORMAT(token, "(type: UNKNOWN, value: '', source location:(file:unknown, line:0, column:0))");
        REQUIRE(token.compat_to_string() == "(typ: UNKNOWN, val: '', sl:(f:unknown, l:0, c:0))");
    }

    TEST_CASE("default constructed token set propriety","[token]") {
        using enum vnd::TokenType;
        vnd::Token token {
        }
        ;
        REQUIRE(token.getType() == UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getFileName() == filename4);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        modifyToken(token);
        REQUIRE(token.getType() == INTEGER);
        REQUIRE(token.getValue().empty() == false);
        REQUIRE(token.getFileName() == filename);
        REQUIRE(token.getLine() == 1);
        REQUIRE(token.getColumn() == 1);
    }

    TEST_CASE("default constructed token isType","[token]") {
        using enum vnd::TokenType;
        vnd::Token token {
        }
        ;
        REQUIRE(token.getType() == UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getFileName() == filename4);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQUIRE(token.isType(CHAR) == false);
        modifyToken(token);
        REQUIRE(token.getType() == INTEGER);
        REQUIRE(token.isType(INTEGER) == true);
        REQUIRE(token.getValue().empty() == false);
        REQUIRE(token.getFileName() == filename);
        REQUIRE(token.getLine() == 1);
        REQUIRE(token.getColumn() == 1);
    }

    TEST_CASE("default constructed token set propriety tostring","[token]") {
        using enum vnd::TokenType;
        vnd::Token token {
        }
        ;
        REQUIRE(token.getType() == UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQUIRE(token.to_string() == "(type: UNKNOWN, value: '', source location:(file:unknown, line:0, column:0))");
        modifyToken(token);
        REQUIRE(token.getType() == INTEGER);
        REQUIRE(token.getValue().empty() == false);
        REQUIRE(token.getFileName() == filename);
        REQUIRE(token.getLine() == 1);
        REQUIRE(token.getColumn() == 1);
#if defined(_WIN32) || defined(__WIN32__) || defined(WIN32)
        REQUIRE(token.to_string() == R"((type: INTEGER, value: 'assss', source location:(file:.unknown.vn, line:1, column:1)))");
        REQUIRE(token.compat_to_string() == R"((typ: INT, val: 'assss', sl:(f:.unknown.vn, l:1, c:1)))");
#else
        REQUIRE(token.to_string() == R"((type: INTEGER, value: 'assss', source location:(file:./unknown.vn, line:1, column:1)))");
        REQUIRE(token.compat_to_string() == R"((typ: INT, val: 'assss', sl:(f:./unknown.vn, l:1, c:1)))");
#endif
    }

    TEST_CASE("construct a empty value token","[token]") {
        using enum vnd::TokenType;
    const vnd::Token token {
            UNKNOWN, vnd::CodeSourceLocation {
            }
        } ;
        REQUIRE(token.getType() == UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQUIRE(token.getFileName() == "unknown");
        REQUIRE(token.to_string() == "(type: UNKNOWN, value: '', source location:(file:unknown, line:0, column:0))");
        REQUIRE(token.compat_to_string() == "(typ: UNKNOWN, val: '', sl:(f:unknown, l:0, c:0))");
    }

    TEST_CASE("construct a EOFT token","[token]") {  // NOLINT(*-function-cognitive-complexity)
        using enum vnd::TokenType;
    const vnd::Token token {
            EOFT, vnd::CodeSourceLocation {
            }
        } ;
        REQUIRE(token.getType() == EOFT);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQUIRE(token.getFileName() == "unknown");
        REQUIRE(token.to_string() == "(type: EOF, source location:(file:unknown, line:0, column:0))");
        REQUIRE(token.compat_to_string() == "(typ: EOF, sl:(f:unknown, l:0, c:0))");
    }

    TEST_CASE("FolderCreationResult Constructor","[FolderCreationResult]") {
        SECTION("Default constructor") {
        const vnd::FolderCreationResult result;
            REQUIRE_FALSE(result.success());
            REQUIRE(result.path().value_or("").empty());
        }

        SECTION("Parameterized constructor") {
        const vnd::FolderCreationResult result(true, fs::path (testPaths));
            REQUIRE(result.success() == true);
            REQUIRE(result.path() == fs::path (testPaths));
        }
    }

    TEST_CASE("FolderCreationResult Setters","[FolderCreationResult]") {
        vnd::FolderCreationResult result;

        SECTION("Set success") {
            result.set_success(true);
            REQUIRE(result.success() == true);
        }

        SECTION("Set path") {
            fs::path testPath(testPaths);
            REQUIRE(result.path().value_or("").empty());
            result.set_path(testPaths);
            REQUIRE(result.path() == testPath);
        }

        SECTION("Set path with empty string") {
            REQUIRE_THROWS_MATCHES(result.set_path(fs::path ()),std::invalid_argument, Message("Path cannot be empty"));
        }
    }

    TEST_CASE("FolderCreationResult operator<< outputs correctly","[FolderCreationResult]") {
        SECTION("Test with successful folder creation and valid path") {
        const fs::path folderPath = "/test/directory";
        const vnd::FolderCreationResult result(true, folderPath);

            std::ostringstream oss;
            oss << result;

            REQUIRE(oss.str() == "success_: true, path_: /test/directory");
        }

        SECTION("Test with unsuccessful folder creation and no path") {
        const vnd::FolderCreationResult result(false, fs::path {
            });

            std::ostringstream oss;
            oss << result;

            REQUIRE(oss.str() == "success_: false, path_: None");
        }

        SECTION("Test with successful folder creation but empty path") {
        const vnd::FolderCreationResult result(true, fs::path {
            });

            std::ostringstream oss;
            oss << result;

            REQUIRE(oss.str() == "success_: true, path_: None");
        }

        SECTION("Test with unsuccessful folder creation and valid path") {
        const fs::path folderPath = "/another/test/directory";
        const vnd::FolderCreationResult result(false, folderPath);

            std::ostringstream oss;
            oss << result;

            REQUIRE(oss.str() == "success_: false, path_: /another/test/directory");
        }

        SECTION("Test with default constructed FolderCreationResult") {
        const vnd::FolderCreationResult result;

            std::ostringstream oss;
            oss << result;

            REQUIRE(oss.str() == "success_: false, path_: None");
        }
    }

    TEST_CASE("FolderCreationResult: Equality and Swap","[FolderCreationResult]") {
        fs::path path1("/folder1");
        fs::path path2("/folder2");

        vnd::FolderCreationResult result1(true, path1);
        vnd::FolderCreationResult result2(false, path2);

        SECTION("Equality operator") {
            REQUIRE(result1 != result2);
            vnd::FolderCreationResult result3(true, path1);
            REQUIRE(result1 == result3);
        }

        SECTION("swap() function") {
            swap(result1, result2);
            REQUIRE(result1.success() == false);
            REQUIRE(result1.path().value() == path2);
            REQUIRE(result2.success() == true);
            REQUIRE(result2.path().value() == path1);
        }
    }

    TEST_CASE("FolderCreationResult Hash Value","[FolderCreationResult]") {
        SECTION("Hash value is consistent for the same object") {
        const vnd::FolderCreationResult result(true, fs::path ("/test/directory"));
        const std::size_t hash1 = hash_value(result);
        const std::size_t hash2 = hash_value(result);

            REQUIRE(hash1 == hash2);
        }

        SECTION("Hash value changes with different success status") {
        const vnd::FolderCreationResult result1(true, fs::path ("/test/directory"));
        const vnd::FolderCreationResult result2(false, fs::path ("/test/directory"));

        const std::size_t hash1 = hash_value(result1);
        const std::size_t hash2 = hash_value(result2);

            REQUIRE(hash1 != hash2);
        }

        SECTION("Hash value changes with different paths") {
        const vnd::FolderCreationResult result1(true, fs::path ("/test/directory"));
        const vnd::FolderCreationResult result2(true, fs::path ("/different/directory"));

        const std::size_t hash1 = hash_value(result1);
        const std::size_t hash2 = hash_value(result2);

            REQUIRE(hash1 != hash2);
        }

        SECTION("Identical objects have the same hash value") {
        const vnd::FolderCreationResult result1(true, fs::path ("/test/directory"));
        const vnd::FolderCreationResult result2(true, fs::path ("/test/directory"));

        const std::size_t hash1 = hash_value(result1);
        const std::size_t hash2 = hash_value(result2);

            REQUIRE(hash1 == hash2);
        }

        SECTION("Different objects have different hash values") {
        const vnd::FolderCreationResult result1(true, fs::path ("/test/directory"));
        const vnd::FolderCreationResult result2(false, fs::path ("/another/directory"));

        const std::size_t hash1 = hash_value(result1);
        const std::size_t hash2 = hash_value(result2);

            REQUIRE(hash1 != hash2);
        }

        SECTION("Hash for default constructed object is consistent") {
        const vnd::FolderCreationResult result1;
        const vnd::FolderCreationResult result2;

        const std::size_t hash1 = hash_value(result1);
        const std::size_t hash2 = hash_value(result2);

            REQUIRE(hash1 == hash2);
        }

        SECTION("Hash for default object vs object with empty path") {
        const vnd::FolderCreationResult result1;
        const vnd::FolderCreationResult result2(false, fs::path {
            });

        const std::size_t hash1 = hash_value(result1);
        const std::size_t hash2 = hash_value(result2);

            REQUIRE(hash1 == hash2);
        }
    }

    TEST_CASE("FolderCreationResult Folder Creation Functions","[FolderCreationResult]") {
        // Create a temporary directory for testing
        auto tempDir = fs::temp_directory_path () / "vnd_test";
    const std::string folderName = "test_folder";
    const fs::path folderPath = tempDir / folderName;
        fs::create_directories (tempDir);

        SECTION("Create folder with valid parameters") {
        const vnd::FolderCreationResult result = vnd::FolderCreationResult::createFolder(folderName, tempDir);
            REQUIRE(result.success() == true);
            REQUIRE(result.path() == folderPath);
        [[maybe_unused]]auto unused = fs::remove_all (folderPath);
        }

        SECTION("Create folder with empty folder name") {
        const std::string emptyFolderName;
        const vnd::FolderCreationResult result = vnd::FolderCreationResult::createFolder(emptyFolderName, tempDir);
            REQUIRE_FALSE(result.success());
            REQUIRE(result.path()->empty());
        }

        SECTION("Create folder in non-existent parent directory") {
        const fs::path nonExistentParentDir = tempDir / "non_existent_dir";
        const vnd::FolderCreationResult result = vnd::FolderCreationResult::
            createFolder(folderName, nonExistentParentDir);
            REQUIRE(result.success() == true);
            REQUIRE(!result.path()->empty());
        }

        SECTION("Create folder in existing directory") {
        const fs::path nonExistentParentDir = tempDir / "non_existent_dir";
        const vnd::FolderCreationResult result = vnd::FolderCreationResult::
            createFolder(folderName, nonExistentParentDir);
            REQUIRE(result.success() == true);
            REQUIRE(!result.path()->empty());
        const std::string folderName2 = "test_folder";
        const vnd::FolderCreationResult result2 = vnd::FolderCreationResult::
            createFolder(folderName2, nonExistentParentDir);
            REQUIRE(result2.success() == true);
            REQUIRE(!result2.path()->empty());
        }

        SECTION("Create folder next to non-existent file") {
        const fs::path nonExistentFilePath = tempDir / "non_existent_file.txt";
        const vnd::FolderCreationResult result = vnd::FolderCreationResult::
            createFolderNextToFile(nonExistentFilePath, folderName);
            REQUIRE(result.success() == true);
            REQUIRE(!result.path()->empty());
            REQUIRE(!result.pathcref()->empty());
        }

        SECTION("Create folder next to existing file") {
            // Create a file in the temporary directory
        const fs::path filePathInner = tempDir / "test_file.txt";
            std::ofstream ofs(filePathInner);
            ofs.close();

        const vnd::FolderCreationResult result = vnd::FolderCreationResult::
            createFolderNextToFile(filePathInner, folderName);
            REQUIRE(result.success() == true);
            REQUIRE(result.path() == folderPath);

        [[maybe_unused]]auto unused = fs::remove (filePathInner);
        [[maybe_unused]]auto unuseds = fs::remove_all (folderPath);
        }
    [[maybe_unused]]auto unused = fs::remove_all (tempDir);
    }


    TEST_CASE("default constructed token set propriety format","[token]") {
        using enum vnd::TokenType;
        vnd::Token token {
        }
        ;
        REQUIRE(token.getType() == UNKNOWN);
        REQUIRE(token.getValue().empty() == true);
        REQUIRE(token.getLine() == 0);
        REQUIRE(token.getColumn() == 0);
        REQUIRE(FORMAT("{}", token) == "(type: UNKNOWN, value: '', source location:(file:unknown, line:0, column:0))");
        modifyToken(token);
        REQUIRE(token.getType() == INTEGER);
        REQUIRE(token.getValue().empty() == false);
        REQUIRE(token.getFileName() == filename);
        REQUIRE(token.getLine() == 1);
        REQUIRE(token.getColumn() == 1);
#if defined(_WIN32) || defined(__WIN32__) || defined(WIN32)
        REQ_FORMAT(token, R"((type: INTEGER, value: 'assss', source location:(file:.unknown.vn, line:1, column:1)))");
#else
        REQ_FORMAT(token, R"((type: INTEGER, value: 'assss', source location:(file:.unknown.vn, line:1, column:1)))");
#endif
    }

    TEST_CASE("Token Comparison Equality","[Token]") {
        vnd::Token token1(vnd::TokenType::PLUS, "+", vnd::CodeSourceLocation (filename, t_line, t_colum));
        vnd::Token token2(vnd::TokenType::PLUS, "+", vnd::CodeSourceLocation (filename, t_line, t_colum));
        REQUIRE(token1 == token2);
    }

    TEST_CASE("Token Comparison Inequality","[Token]") {
        vnd::Token token1(identf, "variable", vnd::CodeSourceLocation (filename, t_line, t_colum));
        vnd::Token token2(identf, "variable2", vnd::CodeSourceLocation (filename, t_line, t_colum));
        REQUIRE(token1 != token2);
    }
    */
    @Test
    void emitIdentifierToken() {
        String code = "a a_ a0 a000_ _a";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(6, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.IDENTIFIER, "a", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "a_", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "a0", new CodeSourceLocation(fileName, 1, 6)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "a000_", new CodeSourceLocation(fileName, 1, 9)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "_a", new CodeSourceLocation(fileName, 1, 15)).toString(),
                tokens.get(4).toString(), "Fifth token mismatch");
    }

    @Test
    void emitIdentifierTokenNewLine() {
        String code = "a a_\na0 a000_ _a";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(6, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.IDENTIFIER, "a", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "a_", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "a0", new CodeSourceLocation(fileName, 2, 1)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "a000_", new CodeSourceLocation(fileName, 2, 4)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");

        assertEquals(new Token(TokenType.IDENTIFIER, "_a", new CodeSourceLocation(fileName, 2, 10)).toString(),
                tokens.get(4).toString(), "Fifth token mismatch");
    }

    @Test
    void emitIntegerTokenForHexadecimalsNumbers() {
        String code = "#0 #23 #24 #ff #7f";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(6, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.INTEGER, "#0", new CodeSourceLocation(fileName, 1, 0)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "#23", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "#24", new CodeSourceLocation(fileName, 1, 7)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "#ff", new CodeSourceLocation(fileName, 1, 11)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "#7f", new CodeSourceLocation(fileName, 1, 15)).toString(),
                tokens.get(4).toString(), "Fifth token mismatch");
    }

    @Test
    void emitExceptionOnMalformedExadecimalNumberOrOctalNumber() {
        String code = "#";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        assertThrows(RuntimeException.class, tokenizer::tokenize, "Malformed exadecimal number or octal number '#' (line 1, column 2):");
    }

    @Test
    void testEmitIntegerTokenFromOctalNumbers(){
        String code = "#o0 #o23 #o24";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(4, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.INTEGER, "#o0", new CodeSourceLocation(fileName, 1, 0)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "#o23", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "#o24", new CodeSourceLocation(fileName, 1, 9)).toString(),
                tokens.get(2).toString(), "Third token mismatch");
    }

    @Test
    void testIntegerToken() {
        String code = "42 333 550 34000000";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(5, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.INTEGER, "42", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "333", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "550", new CodeSourceLocation(fileName, 1, 8)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.INTEGER, "34000000", new CodeSourceLocation(fileName, 1, 12)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");
    }
    @Test
    void testEmitDoubleToken() {
        String code = "1. 1.0 1e+1 1E+1 1.1e+1 1.1E+1 1e-1 1E-1 1.1e-1 1.1E-1 .4e12 4i 5.4if .7f";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(15, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.DOUBLE, "1.", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1.0", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1e+1", new CodeSourceLocation(fileName, 1, 8)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1E+1", new CodeSourceLocation(fileName, 1, 13)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1.1e+1", new CodeSourceLocation(fileName, 1, 18)).toString(),
                tokens.get(4).toString(), "Fifth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1.1E+1", new CodeSourceLocation(fileName, 1, 25)).toString(),
                tokens.get(5).toString(), "Sixth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1e-1", new CodeSourceLocation(fileName, 1, 32)).toString(),
                tokens.get(6).toString(), "Seventh token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1E-1", new CodeSourceLocation(fileName, 1, 37)).toString(),
                tokens.get(7).toString(), "Eighth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1.1e-1", new CodeSourceLocation(fileName, 1, 42)).toString(),
                tokens.get(8).toString(), "Ninth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "1.1E-1", new CodeSourceLocation(fileName, 1, 49)).toString(),
                tokens.get(9).toString(), "Tenth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, ".4e12", new CodeSourceLocation(fileName, 1, 56)).toString(),
                tokens.get(10).toString(), "Eleventh token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "4i", new CodeSourceLocation(fileName, 1, 62)).toString(),
                tokens.get(11).toString(), "Twelfth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, "5.4if", new CodeSourceLocation(fileName, 1, 65)).toString(),
                tokens.get(12).toString(), "Thirteenth token mismatch");

        assertEquals(new Token(TokenType.DOUBLE, ".7f", new CodeSourceLocation(fileName, 1, 71)).toString(),
                tokens.get(13).toString(), "Fourteenth token mismatch");
    }

    @Test
    void testEmitOperatorToken() {
        String code = "* / = , : < > ! | & + - ^ . %";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(16, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.STAR, "*", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.DIVIDE, "/", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.EQUAL, "=", new CodeSourceLocation(fileName, 1, 5)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.COMMA, ",", new CodeSourceLocation(fileName, 1, 7)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");

        assertEquals(new Token(TokenType.COLON, ":", new CodeSourceLocation(fileName, 1, 9)).toString(),
                tokens.get(4).toString(), "Fifth token mismatch");

        assertEquals(new Token(TokenType.LESS, "<", new CodeSourceLocation(fileName, 1, 11)).toString(),
                tokens.get(5).toString(), "Sixth token mismatch");

        assertEquals(new Token(TokenType.GREATER, ">", new CodeSourceLocation(fileName, 1, 13)).toString(),
                tokens.get(6).toString(), "Seventh token mismatch");

        assertEquals(new Token(TokenType.NOT, "!", new CodeSourceLocation(fileName, 1, 15)).toString(),
                tokens.get(7).toString(), "Eighth token mismatch");

        assertEquals(new Token(TokenType.OR, "|", new CodeSourceLocation(fileName, 1, 17)).toString(),
                tokens.get(8).toString(), "Ninth token mismatch");

        assertEquals(new Token(TokenType.AND, "&", new CodeSourceLocation(fileName, 1, 19)).toString(),
                tokens.get(9).toString(), "Tenth token mismatch");

        assertEquals(new Token(TokenType.PLUS, "+", new CodeSourceLocation(fileName, 1, 21)).toString(),
                tokens.get(10).toString(), "Eleventh token mismatch");

        assertEquals(new Token(TokenType.MINUS, "-", new CodeSourceLocation(fileName, 1, 23)).toString(),
                tokens.get(11).toString(), "Twelfth token mismatch");

        assertEquals(new Token(TokenType.XOR, "^", new CodeSourceLocation(fileName, 1, 25)).toString(),
                tokens.get(12).toString(), "Thirteenth token mismatch");

        assertEquals(new Token(TokenType.DOT, ".", new CodeSourceLocation(fileName, 1, 27)).toString(),
                tokens.get(13).toString(), "Fourteenth token mismatch");

        assertEquals(new Token(TokenType.PERCENT, "%", new CodeSourceLocation(fileName, 1, 29)).toString(),
                tokens.get(14).toString(), "Fifteenth token mismatch");
    }

    @Test
    void testEmitOperationEqualToken() {
        String code = "+= -= *= /= %=";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(6, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.PLUSEQUAL, "+=", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.MINUSEQUAL, "-=", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.STAREQUAL, "*=", new CodeSourceLocation(fileName, 1, 7)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.DIVIDEEQUAL, "/=", new CodeSourceLocation(fileName, 1, 10)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");

        assertEquals(new Token(TokenType.PERCENTEQUAL, "%=", new CodeSourceLocation(fileName, 1, 13)).toString(),
                tokens.get(4).toString(), "Fifth token mismatch");
    }

    @Test
    void testEmitBooleanOperatorToken() {
        String code = "== >= <= !=";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(5, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.EQUALEQUAL, "==", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.GREATEREQUAL, ">=", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.LESSEQUAL, "<=", new CodeSourceLocation(fileName, 1, 7)).toString(),
                tokens.get(2).toString(), "Third token mismatch");

        assertEquals(new Token(TokenType.NOTEQUAL, "!=", new CodeSourceLocation(fileName, 1, 10)).toString(),
                tokens.get(3).toString(), "Fourth token mismatch");
    }

    @Test
    void testEmitLogicalOperatorToken() {
        String code = "&& ||";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(3, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.ANDAND, "&&", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.OROR, "||", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");
    }

    @Test
    void testEmitUnaryOperatorToken() {
        String code = "++ --";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(3, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.PLUSPLUS, "++", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.MINUSMINUS, "--", new CodeSourceLocation(fileName, 1, 4)).toString(),
                tokens.get(1).toString(), "Second token mismatch");
    }

    @Test
    void testEmitParenthesisToken() {
        String code = "( )";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(3, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.OPEN_PARENTESIS, "(", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.CLOSE_PARENTESIS, ")", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");
    }

    @Test
    void testEmitSquareParenthesisToken() {
        String code = "[ ]";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(3, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.OPEN_SQ_PARENTESIS, "[", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.CLOSE_SQ_PARENTESIS, "]", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");
    }

    @Test
    void testEmitCurlyParenthesisToken() {
        String code = "{ }";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(3, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.OPEN_CUR_PARENTESIS, "{", new CodeSourceLocation(fileName, 1, 1)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.CLOSE_CUR_PARENTESIS, "}", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(1).toString(), "Second token mismatch");
    }


    @Test
    void testEmitCharToken() {
        String code = "'a' '\\\\' ''";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(4, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.CHAR, "a", new CodeSourceLocation(fileName, 1, 2)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.CHAR, "\\\\", new CodeSourceLocation(fileName, 1, 6)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.CHAR, "", new CodeSourceLocation(fileName, 1, 11)).toString(),
                tokens.get(2).toString(), "Third token mismatch");
    }

    @Test
    void testEmitExceptionOnUnknownChar() {
        String code = ";";
        String filename = "testFile";

        Lexer tokenizer = new Lexer(filename, code);
        assertThrows(RuntimeException.class, tokenizer::tokenize, "Unknown Character ';' (line 1, column 1):");
    }

    @Test
    void testEmitStringToken() {
        String filename = "testFile";
        String code2 = "\"a\" \"\\\\\" \"\"";
        //String code2 = "\"a\"\"\\\\\"\""; // equivalent to "a""\\"" in Java string literal

        Lexer tokenizer = new Lexer(filename, code2);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(4, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.STRING, "a", new CodeSourceLocation(fileName, 1, 3)).toString(),
                tokens.get(0).toString(), "First token mismatch");

        assertEquals(new Token(TokenType.STRING, "\\\\", new CodeSourceLocation(fileName, 1, 7)).toString(),
                tokens.get(1).toString(), "Second token mismatch");

        assertEquals(new Token(TokenType.STRING, "", new CodeSourceLocation(fileName, 1, 12)).toString(),
                tokens.get(2).toString(), "Third token mismatch");
    }

    @Test
    void testEmitUnknownTokenOnNonClosedCharToken() {
        // Arrange: Define the input code and expected tokens
        String code2 = "'a"; // Non-closed char token
        String filename = "testfile.txt";

        Lexer tokenizer = new Lexer(filename, code2);

        // Act: Tokenize the input code
        List<Token> tokens = tokenizer.tokenize();

        // Assert: Validate the results
        assertEquals(2, tokens.size(), "Expected exactly 2 tokens.");

        Token expectedToken = new Token(
                TokenType.UNKNOWN,
                "a",
                new CodeSourceLocation(new FileName(filename), 1, 2)
        );

        assertEquals(expectedToken, tokens.getFirst(), "First token should be an UNKNOWN token with 'a'.");
    }

    @Test
    void testBasicSingleLineComment() {
        String code = "// line comment";
        String filename = "testfile.java";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(2, tokens.size());
        FileName fileName = new FileName(filename);
        assertEquals(new Token(TokenType.COMMENT, "// line comment", new CodeSourceLocation(fileName, 1, 1)), tokens.get(0));
    }

    @Test
    void testSingleLineCommentWithSymbols() {
        String input = "// Comment with symbols !@#$%^&*()123456\n";
        String filename = "testfile.java";

        Lexer tokenizer = new Lexer(filename, input);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(2, tokens.size());
        assertEquals(TokenType.COMMENT, tokens.get(0).getType());
        assertEquals("// Comment with symbols !@#$%^&*()123456", tokens.get(0).getValue());
    }

    @Test
    void testSingleLineCommentWithNoText() {
        String input = "//\n";
        String filename = "testfile.java";

        Lexer tokenizer = new Lexer(filename, input);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(2, tokens.size());
        assertEquals(TokenType.COMMENT, tokens.get(0).getType());
        assertEquals("//", tokens.get(0).getValue());
    }


    @Test
    void testBasicMultiLineComment() {
        final String code = "/*multi\nline\ncomment*/";
        String filename = "exampleFile.java";

        Lexer tokenizer = new Lexer(filename, code);
        List<Token> tokens = tokenizer.tokenize();

        FileName fileName = new FileName(filename);
        assertEquals(2, tokens.size());
        assertEquals(new Token(TokenType.COMMENT, "/*multi\nline\ncomment*/", new CodeSourceLocation(fileName, 2, 1)).toString(), tokens.get(0).toString());
    }

    @Test
    void testMultiLineCommentWithAsterisksInside() {
        final String input = "/* This * is * a * multi-line comment */";
        String filename = "exampleFile.java";

        Lexer tokenizer = new Lexer(filename, input);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(2, tokens.size());
        assertEquals(TokenType.COMMENT, tokens.get(0).getType());
        assertEquals("/* This * is * a * multi-line comment */", tokens.get(0).getValue());
    }


    @Test
    public void testSingleAndMultiLineComments() {
        String input = "// Single line\n/* Multi-line */";
        Lexer tokenizer = new Lexer("testFile", input);

        List<Token> tokens = tokenizer.tokenize();

        assertEquals(TokenType.COMMENT, tokens.getFirst().getType());
        assertEquals("// Single line", tokens.getFirst().getValue());

        assertEquals(TokenType.COMMENT, tokens.get(1).getType());
        assertEquals("/* Multi-line */", tokens.get(1).getValue());
    }

    @Test
    public void testMultiLineCommentFollowedBySingleLineComment() {
        String input = "/* Multi-line */\n// Single line";
        Lexer tokenizer = new Lexer("testFile", input);

        List<Token> tokens = tokenizer.tokenize();

        assertEquals(TokenType.COMMENT, tokens.getFirst().getType());
        assertEquals("/* Multi-line */", tokens.getFirst().getValue());

        assertEquals(TokenType.COMMENT, tokens.get(1).getType());
        assertEquals("// Single line", tokens.get(1).getValue());
    }


    @Test
    void commentEdgeCase() {
        //const std::string input = "/* Comment with ** inside */";
        //vnd::Tokenizer tokenizer(input, "testFile");
        String input = "/* Comment with ** inside */";
        Lexer tokenizer = new Lexer("testFile", input);

        Token token = tokenizer.tokenize().getFirst();
        assertSame(TokenType.COMMENT, token.getType());
        assertSame("/* Comment with ** inside */", token.getValue());
    }

    /*@Test
    void tokenize() {
    }*/
}