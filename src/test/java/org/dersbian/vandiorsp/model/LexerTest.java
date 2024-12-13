package org.dersbian.vandiorsp.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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

    TEST_CASE("tokenizer emit identifier token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "a a_ a0 a000_ _a", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 6);
        REQUIRE(tokens[0] == vnd::Token (identf, "a", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (identf, "a_", vnd::CodeSourceLocation (filename, 1, 3)));
        REQUIRE(tokens[2] == vnd::Token (identf, "a0", vnd::CodeSourceLocation (filename, 1, 6)));
        REQUIRE(tokens[3] == vnd::Token (identf, "a000_", vnd::CodeSourceLocation (filename, 1, t_colum5)));
        REQUIRE(tokens[4] == vnd::Token (identf, "_a", vnd::CodeSourceLocation (filename, 1, t_colum10)));
    }

    TEST_CASE("tokenizer emit identifier token new line","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "a a_\na0 a000_ _a", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 6);
        REQUIRE(tokens[0] == vnd::Token (identf, "a", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (identf, "a_", vnd::CodeSourceLocation (filename, 1, 3)));
        REQUIRE(tokens[2] == vnd::Token (identf, "a0", vnd::CodeSourceLocation (filename, 2, 1)));
        REQUIRE(tokens[3] == vnd::Token (identf, "a000_", vnd::CodeSourceLocation (filename, 2, 4)));
        REQUIRE(tokens[4] == vnd::Token (identf, "_a", vnd::CodeSourceLocation (filename, 2, 10)));
    }

    TEST_CASE("tokenizer emit integer token for hexadecimals numbers","[tokenizer]") {
        // hexadecimals 0xhexnum a-f A-F 0-9
        vnd::Tokenizer tokenizer {
            "#0 #23 #24 #ff #7f", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 6);
        REQUIRE(tokens[0] == vnd::Token (inte, "#0", vnd::CodeSourceLocation (filename, 1, 0)));
        REQUIRE(tokens[1] == vnd::Token (inte, "#23", vnd::CodeSourceLocation (filename, 1, 3)));
        REQUIRE(tokens[2] == vnd::Token (inte, "#24", vnd::CodeSourceLocation (filename, 1, t_colum3)));
        REQUIRE(tokens[3] == vnd::Token (inte, "#ff", vnd::CodeSourceLocation (filename, 1, t_colum8)));
        REQUIRE(tokens[4] == vnd::Token (inte, "#7f", vnd::CodeSourceLocation (filename, 1, t_colum10)));
    }

    TEST_CASE("tokenizer emit exception on  malformed exadecimal number or octal number","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "#", filename
        } ;
        REQUIRE_THROWS_MATCHES(tokenizer.tokenize(), std::runtime_error,
                MessageMatches(ContainsSubstring("malformed exadecimal number or octal number '#' (line 1, column 2):")));
    }

    TEST_CASE("tokenizer emit integer token for octal numbers","[tokenizer]") {
        // octal 0oOctnum 0-7
        vnd::Tokenizer tokenizer {
            "#o0 #o23 #o24", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 4);
        REQUIRE(tokens[0] == vnd::Token (inte, "#o0", vnd::CodeSourceLocation (filename, 1, 0)));
        REQUIRE(tokens[1] == vnd::Token (inte, "#o23", vnd::CodeSourceLocation (filename, 1, 4)));
        REQUIRE(tokens[2] == vnd::Token (inte, "#o24", vnd::CodeSourceLocation (filename, 1, t_colum5)));
    }

    TEST_CASE("tokenizer emit integer token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "42 333 550 34000000", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 5);
        REQUIRE(tokens[0] == vnd::Token (inte, "42", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (inte, "333", vnd::CodeSourceLocation (filename, 1, 4)));
        REQUIRE(tokens[2] == vnd::Token (inte, "550", vnd::CodeSourceLocation (filename, 1, t_colum4)));
        REQUIRE(tokens[3] == vnd::Token (inte, "34000000", vnd::CodeSourceLocation (filename, 1, t_colum14)));
    }

    TEST_CASE("tokenizer emit integer token new line","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "42 333\n550 34000000", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 5);
        REQUIRE(tokens[0] == vnd::Token (inte, "42", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (inte, "333", vnd::CodeSourceLocation (filename, 1, 4)));
        REQUIRE(tokens[2] == vnd::Token (inte, "550", vnd::CodeSourceLocation (filename, 2, 1)));
        REQUIRE(tokens[3] == vnd::Token (inte, "34000000", vnd::CodeSourceLocation (filename, 2, 5)));
    }

    TEST_CASE("tokenizer emit double token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "1. 1.0 1e+1 1E+1 1.1e+1 1.1E+1 1e-1 1E-1 1.1e-1 1.1E-1 .4e12 4i 5.4if .7f", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 15);
        REQUIRE(tokens[0] == vnd::Token (doub, "1.", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (doub, "1.0", vnd::CodeSourceLocation (filename, 1, 4)));
        REQUIRE(tokens[2] == vnd::Token (doub, "1e+1", vnd::CodeSourceLocation (filename, 1, t_colum4)));
        REQUIRE(tokens[3] == vnd::Token (doub, "1E+1", vnd::CodeSourceLocation (filename, 1, t_colum11)));
        REQUIRE(tokens[4] == vnd::Token (doub, "1.1e+1", vnd::CodeSourceLocation (filename, 1, 18)));
        REQUIRE(tokens[5] == vnd::Token (doub, "1.1E+1", vnd::CodeSourceLocation (filename, 1, 25)));
        REQUIRE(tokens[6] == vnd::Token (doub, "1e-1", vnd::CodeSourceLocation (filename, 1, 32)));
        REQUIRE(tokens[7] == vnd::Token (doub, "1E-1", vnd::CodeSourceLocation (filename, 1, 37)));
        REQUIRE(tokens[8] == vnd::Token (doub, "1.1e-1", vnd::CodeSourceLocation (filename, 1, 42)));
        REQUIRE(tokens[9] == vnd::Token (doub, "1.1E-1", vnd::CodeSourceLocation (filename, 1, 49)));
        REQUIRE(tokens[10] == vnd::Token (doub, ".4e12", vnd::CodeSourceLocation (filename, 1, 56)));
        REQUIRE(tokens[11] == vnd::Token (doub, "4i", vnd::CodeSourceLocation (filename, 1, 62)));
        REQUIRE(tokens[12] == vnd::Token (doub, "5.4if", vnd::CodeSourceLocation (filename, 1, 65)));
        REQUIRE(tokens[13] == vnd::Token (doub, ".7f", vnd::CodeSourceLocation (filename, 1, 71)));
    }

    TEST_CASE("tokenizer emit operator token","[tokenizer]") {
        using enum vnd::TokenType;
        vnd::Tokenizer tokenizer {
            "* / = , : < > ! | & + - ^ . %", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 16);
        REQUIRE(tokens[0] == vnd::Token (STAR, "*", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (DIVIDE, "/", vnd::CodeSourceLocation (filename, 1, 3)));
        REQUIRE(tokens[2] == vnd::Token (EQUAL, "=", vnd::CodeSourceLocation (filename, 1, 5)));
        REQUIRE(tokens[3] == vnd::Token (COMMA, ",", vnd::CodeSourceLocation (filename, 1, t_colum + 1)));
        REQUIRE(tokens[4] == vnd::Token (COLON, ":", vnd::CodeSourceLocation (filename, 1, t_colum4 + 1)));
        // REQUIRE(tokens[5].getColumn() == t_colum8);
        REQUIRE(tokens[5] == vnd::Token (LESS, "<", vnd::CodeSourceLocation (filename, 1, t_colum8)));
        REQUIRE(tokens[6] == vnd::Token (GREATER, ">", vnd::CodeSourceLocation (filename, 1, t_colum11)));
        REQUIRE(tokens[7] == vnd::Token (NOT, "!", vnd::CodeSourceLocation (filename, 1, t_colum10)));
        REQUIRE(tokens[8] == vnd::Token (OR, "|", vnd::CodeSourceLocation (filename, 1, t_colum13)));
        REQUIRE(tokens[9] == vnd::Token (AND, "&", vnd::CodeSourceLocation (filename, 1, 19)));
        REQUIRE(tokens[10] == vnd::Token (PLUS, "+", vnd::CodeSourceLocation (filename, 1, 21)));
        REQUIRE(tokens[11] == vnd::Token (MINUS, "-", vnd::CodeSourceLocation (filename, 1, 23)));
        REQUIRE(tokens[12] == vnd::Token (XOR, "^", vnd::CodeSourceLocation (filename, 1, 25)));
        REQUIRE(tokens[13] == vnd::Token (DOT, ".", vnd::CodeSourceLocation (filename, 1, 27)));
        REQUIRE(tokens[14] == vnd::Token (PERCENT, "%", vnd::CodeSourceLocation (filename, 1, 29)));
    }

    TEST_CASE("tokenizer emit operationEqual token","[tokenizer]") {
        using enum vnd::TokenType;
        vnd::Tokenizer tokenizer {
            "+= -= *= /= %=", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 6);
        REQUIRE(tokens[0] == vnd::Token (PLUSEQUAL, "+=", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (MINUSEQUAL, "-=", vnd::CodeSourceLocation (filename, 1, 4)));
        REQUIRE(tokens[2] == vnd::Token (STAREQUAL, "*=", vnd::CodeSourceLocation (filename, 1, t_colum3)));
        REQUIRE(tokens[3] == vnd::Token (DIVIDEEQUAL, "/=", vnd::CodeSourceLocation (filename, 1, 10)));
        REQUIRE(tokens[4] == vnd::Token (PERCENTEQUAL, "%=", vnd::CodeSourceLocation (filename, 1, 13)));
    }

    TEST_CASE("tokenizer emit boolean operator token","[tokenizer]") {
        using enum vnd::TokenType;
        vnd::Tokenizer tokenizer {
            "== >= <= !=", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 5);
        REQUIRE(tokens[0] == vnd::Token (EQUALEQUAL, "==", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (GREATEREQUAL, ">=", vnd::CodeSourceLocation (filename, 1, 4)));
        REQUIRE(tokens[2] == vnd::Token (LESSEQUAL, "<=", vnd::CodeSourceLocation (filename, 1, t_colum3)));
        REQUIRE(tokens[3] == vnd::Token (NOTEQUAL, "!=", vnd::CodeSourceLocation (filename, 1, 10)));
    }

    TEST_CASE("tokenizer emit logical operator token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "&& ||", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 3);
        REQUIRE(tokens[0] == vnd::Token (vnd::TokenType::ANDAND, "&&", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (vnd::TokenType::OROR, "||", vnd::CodeSourceLocation (filename, 1, 4)));
    }

    TEST_CASE("tokenizer emit unary operator token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "++ --", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 3);
        REQUIRE(tokens[0] == vnd::Token (vnd::TokenType::PLUSPLUS, "++", vnd::CodeSourceLocation (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (vnd::TokenType::MINUSMINUS, "--", vnd::CodeSourceLocation (filename, 1, 4)));
    }

    TEST_CASE("tokenizer emit parenthesis token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "( )", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 3);
        REQUIRE(tokens[0] == vnd::Token (vnd::TokenType::OPEN_PARENTESIS, "(", vnd::CodeSourceLocation (filename, 1, 1)))
        ;
        REQUIRE(tokens[1] == vnd::Token (vnd::TokenType::CLOSE_PARENTESIS, ")", vnd::CodeSourceLocation (filename, 1, 3)))
        ;
    }

    TEST_CASE("tokenizer emit square parenthesis token","[tokenizer]") {
        vnd::Tokenizer tokenizer {
            "[ ]", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 3);
        REQUIRE(tokens[0] == vnd::Token (vnd::TokenType::OPEN_SQ_PARENTESIS, "[", vnd::CodeSourceLocation
        (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (vnd::TokenType::CLOSE_SQ_PARENTESIS, "]", vnd::CodeSourceLocation
        (filename, 1, 3)));
    }

    TEST_CASE("Tokenizer emit square curly token","[Tokenizer]") {
        vnd::Tokenizer tokenizer {
            "{ }", filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 3);
        REQUIRE(tokens[0] == vnd::Token (vnd::TokenType::OPEN_CUR_PARENTESIS, "{", vnd::CodeSourceLocation
        (filename, 1, 1)));
        REQUIRE(tokens[1] == vnd::Token (vnd::TokenType::CLOSE_CUR_PARENTESIS, "}", vnd::CodeSourceLocation
        (filename, 1, 3)));
    }*/

    /*TEST_CASE("Tokenizer emit char token","[Tokenizer]") {
        using enum vnd::TokenType;
        constexpr std::string_view code2 = R "('a' '\\' '')";
        vnd::Tokenizer tokenizer {
            code2, filename
        } ;
        std::vector < vnd::Token > tokens = tokenizer.tokenize();
        REQUIRE(tokens.size() == 4);
        REQUIRE(tokens[0] == vnd::Token (CHAR, "a", vnd::CodeSourceLocation (filename, 1, 2)));
        REQUIRE(tokens[1] == vnd::Token (CHAR, R "(\\)", vnd::CodeSourceLocation (filename, 1, t_colum)));
        REQUIRE(tokens[2] == vnd::Token (CHAR, "", vnd::CodeSourceLocation (filename, 1, t_colum8)));
    }*/

    /*TEST_CASE("Tokenizer emit exception for unknown char","[Tokenizer]") {
        vnd::Tokenizer tokenizer {
            ";", filename
        } ;
        REQUIRE_THROWS_MATCHES(tokenizer.tokenize(), std::runtime_error,
                MessageMatches(ContainsSubstring("Unknown Character ';' (line 1, column 1):")));
    }*/

    @Test
    void testEmitStringToken() {
        String filename = "testFile";
        String code2 = "\"a\" \"\\\\\" \"\"";
        //String code2 = "\"a\"\"\\\\\"\""; // equivalent to "a""\\"" in Java string literal

        Lexer tokenizer = new Lexer(filename, code2);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(4, tokens.size(), "Token count mismatch");
        FileName fileName = new FileName(filename);

        assertEquals(new Token(TokenType.STRING, "a", new CodeSourceLocation(fileName, 1, 3)),
                tokens.get(0), "First token mismatch");

        assertEquals(new Token(TokenType.STRING, "\\\\", new CodeSourceLocation(fileName, 1, 7)),
                tokens.get(1), "Second token mismatch");

        assertEquals(new Token(TokenType.STRING, "", new CodeSourceLocation(fileName, 1, 12)),
                tokens.get(2), "Third token mismatch");
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

        assertEquals(expectedToken, tokens.get(0), "First token should be an UNKNOWN token with 'a'.");
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

        Lexer tokenizer = new Lexer(filename,code);
        List<Token> tokens = tokenizer.tokenize();

        FileName fileName = new FileName(filename);
        assertEquals(2, tokens.size());
        assertEquals(new Token(TokenType.COMMENT, "/*multi\nline\ncomment*/", new CodeSourceLocation(fileName, 2, 1)), tokens.get(0));
    }

    @Test
    void testMultiLineCommentWithAsterisksInside() {
        final String input = "/* This * is * a * multi-line comment */";
        String filename = "exampleFile.java";

        Lexer tokenizer = new Lexer(filename,input);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(2, tokens.size());
        assertEquals(TokenType.COMMENT, tokens.get(0).getType());
        assertEquals("/* This * is * a * multi-line comment */", tokens.get(0).getValue());
    }


    @Test
    public void testSingleAndMultiLineComments() {
        String input = "// Single line\n/* Multi-line */";
        Lexer tokenizer = new Lexer("testFile",input);

        List<Token> tokens = tokenizer.tokenize();

        assertEquals(TokenType.COMMENT, tokens.getFirst().getType());
        assertEquals("// Single line", tokens.getFirst().getValue());

        assertEquals(TokenType.COMMENT, tokens.get(1).getType());
        assertEquals("/* Multi-line */", tokens.get(1).getValue());
    }

    @Test
    public void testMultiLineCommentFollowedBySingleLineComment() {
        String input = "/* Multi-line */\n// Single line";
        Lexer tokenizer = new Lexer("testFile",input);

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
        Lexer tokenizer = new Lexer("testFile",input );

        Token token = tokenizer.tokenize().getFirst();
        assertSame(TokenType.COMMENT, token.getType());
        assertSame("/* Comment with ** inside */", token.getValue());
    }

    /*@Test
    void tokenize() {
    }*/
}