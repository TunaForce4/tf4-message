package com.tunaforce.message.api;

import com.slack.api.methods.SlackApiException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> customExceptionHandler(HttpServletRequest request, final ApiException e) {
    	ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    			.status(e.getApiStatus().getStatusCode())
    			.message(e.getApiStatus().getMessage())
    			.errors(null)
    			.build();

        return ResponseEntity
                .ok(apiResponse);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> dataIntegrityViolationExceptionHandler(HttpServletRequest request, final DataIntegrityViolationException dataIntegrityViolationException) {
    	ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    			.status(ApiStatus.REQUIRED_ERROR.getStatusCode())
    			.message(ApiStatus.REQUIRED_ERROR.getMessage())
    			.errors(dataIntegrityViolationException.getMessage())
    			.build();

        return ResponseEntity
                .ok(apiResponse);
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFoundExceptionHandler(HttpServletRequest request, final NoResourceFoundException noResourceFoundException) {
    	ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    			.status(ApiStatus.NOT_FOUND.getStatusCode())
    			.message(ApiStatus.NOT_FOUND.getMessage())
    			.errors(noResourceFoundException.getMessage())
    			.build();

        return ResponseEntity
                .ok(apiResponse);
    }
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<Object>> noSuchElementExceptionHandler(HttpServletRequest request, final NoSuchElementException NoSuchElementException) {
		ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
				.status(ApiStatus.NOT_FOUND.getStatusCode())
				.message(ApiStatus.NOT_FOUND.getMessage())
				.errors(NoSuchElementException.getMessage())
				.build();

		return ResponseEntity
				.ok(apiResponse);
	}

	@ExceptionHandler(SlackApiException.class)
	public ResponseEntity<ApiResponse<Object>> slackApiExceptionHandler(HttpServletRequest request, final SlackApiException slackApiException) {
		ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
				.status(ApiStatus.NOT_FOUND.getStatusCode())
				.message(ApiStatus.NOT_FOUND.getMessage())
				.errors(slackApiException.getMessage())
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}

	@ExceptionHandler(SlackMessageSendException.class)
	public ResponseEntity<ApiResponse<Object>> handleSlackMessageSendException(SlackMessageSendException ex) {
		ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())  // 500 상태 코드
				.message("Slack 메시지 전송 실패")
				.errors(ex.getMessage())
				.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}

}