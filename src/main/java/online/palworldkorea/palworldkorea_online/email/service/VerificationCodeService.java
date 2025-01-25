package online.palworldkorea.palworldkorea_online.email.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.VerificationCodeNotValidException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationCodeService {
    private static final long VERIFICATION_CODE_EXPIRATION_TIME = 5 * 60 * 1000;
    private static final long VERIFICATION_CODE_MAP_CLEANUP_TIME = 60 * 60 * 1000;

    private final Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    public String generateVerificationCode(String email) {
        String verificationCode = String.format("%06d", new Random().nextInt(1000000));
        verificationCodes.put(email, new VerificationCode(verificationCode, LocalDateTime.now()));
        return verificationCode;
    }

    public boolean validateVerificationCode(String email, String verificationCode) {
        VerificationCode serverVerificationCode = verificationCodes.get(email);

        validateGeneratedTime(email, serverVerificationCode);

        return serverVerificationCode.getCode().equals(verificationCode);
    }

    @Scheduled(fixedDelay = VERIFICATION_CODE_MAP_CLEANUP_TIME)
    public void clearVerificationCode() {
        LocalDateTime now = LocalDateTime.now();
        verificationCodes.entrySet()
                .removeIf(entry ->
                        Duration.between(entry.getValue().getGeneratedTime(), now).toMillis()
                                > VERIFICATION_CODE_EXPIRATION_TIME);
    }

    private void validateGeneratedTime(String email, VerificationCode serverVerificationCode) {
        if (serverVerificationCode == null ||
                Duration.between(serverVerificationCode.getGeneratedTime(), LocalDateTime.now()).toMinutes()
                        > VERIFICATION_CODE_EXPIRATION_TIME) {
            verificationCodes.remove(email);
            throw new VerificationCodeNotValidException();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
        LocalDateTime generatedTime;
    }
}
