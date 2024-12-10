package org.dersbian.vandiorsp.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    private static final String SECRET_KEY = "a6512a226825e0e9028eaa7512a18dea1775422cf7712b804397bb8c1fffe49490273e8a2d32d93f7fb84130c68f3afd54a08a05a5130bf08a1183787d6b7ab78d3576b8f86d3a1eada718f8cf959166a63a8242bf583a19e12d37ba01be89916b09112e043fc8f4b53301016052c82fe3eb58408644ba6890f157020d8e7e4d5e4ab0b1f0f8f1b60ef97b2c13dde814f51cf5876b8a02e94760365d895ce4c04fd476c76b7dd01ce961e2b531a9758decaf71082f1529ac29f33ecac81c29f72e1cb7dfa85ac2b40591f8ef8c5440a99ec759fc3a276f0731d7a836bdf1daf3aafbfc760015a48c5c564b33675eb03582202582b27f4e12c9321b54ab4dd4efd9c1eabc643edb8bcf05af492740c87fad823b08d520ddef2d152dd964c49c5051abd6b306b806800473d69b32f1f198456c0c14acd3e50c9fe3c2df6f0602955f2494c357d89bb091fa60cc29190b17ad60f7cb57a0748476f4e7ecefbb065220ea10279779c19921f89ee00c9e60d151a629e0561dfd6ee5d84eb8b69d1af5a91569ac960995e34e12c9aaeb0aa1d542fc4d1d754696448c499360e2eaf060c4ac7906f25413d632d56d5e5c17bcbc4d37fdfda879015958bfb656dede0964ac2eed0723a99b701e0ff69ec7353e535bd46722f0a7d4919bc8527659c67dfc28082c02dea6001e56d6350f89f5145e3bbd4f8bc280704c2949f28775206bcd3735e5e1b4cfb3d118cce2b935231214804493a7b926554afb52c03ae4df57a3b06d91006874109df58361d5b30e00df18d9e621e5693915ec37310433e06e981277d6193a4802a23a93091820a46694fd1cadaca1007c36fb5f630dcaa5d72439caaeb945f98ac8cfd1a38ec9e7363b8e3b99541ebb688d0407dc1989844f84d46c64b9c052faa000d8fbb9a9912d878bf47e23efeb8a17985d4eaf0f9e8fe3ac58b202a1a729e95770ae4f3dd603028dbd665df68e0d944c682fe089cf06cd4ca27bf938fff0897c3c202ef1da5a3d3c901352f288e9e99310063c3990c49aa084629db88356338fea583f5528f1d422dae8edab196628b2fc1a15b6bdda3fdaaf74d1e425562215b4666854059bbc0dbbb31049c9f3e8eee889002d82796a0496d399554f706aacec55dc7f4815c6500b83c4b37e471b1dfea2272a360a295a90dbaf0e7d52be9b9e98c47463c7be9ac8edcaba9c230376037a96d5ce9afac25cc071292f14f9c6b26df2b8ff27e8b91a7681357cc97b6d1ef1eda34d38c9ee6bb83e17e0bce0b55e34ffaae86fbe5b1645f42981ffdf71839ade841a038dbe6c22be216760d0b8b2990d9adaf0e5401534e1e252054d1966285938bb44196109a984d5ae233d16c3f28f3d1af67d0c978258ce3842d4b1434805b518d2a313b59e441c912ce1f3bd6e38d7243e6d4a34850269feac7d144a654cf2c6f22348bed8d0be942d437d6523820330082a8d7c981df977baf1d0fa336ddafd531ff7e3e3653baaebea28cfb3bd3b923040af78b16b504ca998c86f6199e9943d32d8342ce9f27813899c147106e603664a2ecb144f42dd4c6520abe6e65ec9c67fb8a783b2a4c0881ae59df10727dfcbb692a20a740dc198f91b9770378fd94450c26bdb33b8b098b81e1fb651d3f82f5bc4c71786ecedecd495a4bfbe65f60a16819a1d7951a1b206a953f9650220917658d616471673e86cc96d99cb151605c82d7ebebb48f804eb0030bdfd7675cef9d9b5c0629e473a50e490f435b0b7b8a26504dc9bbfeb27ac0d44ac64fadf694101a2e6ad9240320aeeb183ea7b4da02de4dd9349c5f87655767b999a2505102ca24509a21a92050de63455b6b3e393ea6562e982227bca54d923e392372e46221c9d7a532827311376e9f4ea0e68e7a1565d279cc93d7740ff5d7956ea6f66e15360dd91e58139e85824485e6881acc66f0b3546245faaa746057988e2f28dcd46ebdcc39506b6ce480001cf2decf66f6b43a18704c92183cb292db63bf197e5573c3976ef58869f4558c1b4d8cfebe58a0cff20a7745f6eb4cc633559df955a908e7143d3da6d838138ec07f5195995844e54bb48c3ae1dd5316fd0b6eae3a2fd18156ef8f70a7ca3e9182f4d4d28d285451ec8f0d458017d121dca6e2c93bde3ce651870bed6e6948a78d3c22f993c3749b1cb0c1013e2aa35ab1e0ca6d49e9a848480f47956e41f5656d4c8e6caa1bb4a94c9dda22e2bf9d68eb7f8f6a93b08a54ac2b8d8c5a7e1c6796d8c6f20a0364d48e33521354d7ea9c43a2e1e683de5d1c1303d5cf10ed826cc5049f81807dd560ebebd7ef6a3a1218444289b639cd04963abe0205d8741f9804f36cfa77275fd62e448c34fe58d7f2fe1b9e22ffd04c09fcd8f8b7e2da111c44e140a7b0efc778f7eb695815a57e8207f0397475c1f27d117f46e841d0c08a92f4f92b4c5ef70f2fd9f93786d4c4406d9af4788765b64bd649a34a335ebf6565e8308f396514daca6b5d7a4dd06831c2571472442f5545c0af41ba5f6d1c157e928edea25aec1d7528e5df805b3300ad645216b4561b2cf12a5d4bbeb9f24012654e96fa04bb8cffc1d6a1a7ffd11003137ffeeac5a232630d481f9d77adac6a99e43d3d1f53eafb9de47c922a116fbcddd384f4e7e4f5e9cbf382d5f4f0495ab1429f7b68d209e332f344d0ab6a1d91e8654aa5e06901ca2cad2c4ba4f91d310e45882a0a4ac6cf453f54ab16e3cc955baf495fc87edc54db008b49155cfe61b94fa52248a5b93a415671fdcce0ade10ab3d1091dc4011d7febd7ed3fa64e2734ca56530f5a9cbc80063b2661dbe67fd818e1bd1957717d07ed80f2e82df4862f2ac2db77fc85d59b77655ce5aa3e3d240c187d3315e3ac21c3df31cc118b16ee3d1bc43";

    /**
     * Extracts the username (subject) from the given JWT token.
     *
     * @param token the JWT token
     * @return the username embedded in the token
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the given JWT token using the provided resolver function.
     *
     * @param token          the JWT token
     * @param claimsResolver the resolver function to extract the desired claim
     * @param <T>            the type of the claim being extracted
     * @return the extracted claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims((token));
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a new JWT token for the provided user details.
     *
     * @param userDetails the user details
     * @return the generated JWT token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a new JWT token with additional claims for the provided user details.
     *
     * @param extraClaims additional claims to include in the token
     * @param userDetails the user details
     * @return the generated JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey())
                .compact();

    }

    /**
     * Validates the given token by checking its expiration and matching the username.
     *
     * @param token       the JWT token
     * @param userDetails the user details to validate against
     * @return true if the token is valid; false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the given token has expired.
     *
     * @param token the JWT token
     * @return true if the token is expired; false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given token.
     *
     * @param token the JWT token
     * @return the expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the given token.
     *
     * @param token the JWT token
     * @return the claims embedded in the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Retrieves the signing key for JWT generation and verification.
     *
     * @return the signing key
     */
    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

}
