package com.mypractice.auth.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomGraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    @Nullable
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType errorType = determineErrorType(ex);
        String message = buildErrorMessage(ex);
        return GraphqlErrorBuilder.newError(env)
                .message(message)
                .errorType(errorType)
                .build();
    }

    private ErrorType determineErrorType(Throwable ex) {
        if (ex instanceof UserNotFoundException) {
            return ErrorType.NOT_FOUND;
        }
        return ErrorType.INTERNAL_ERROR;
    }

    private String buildErrorMessage(Throwable ex) {
        if (ex instanceof UserNotFoundException) {
            return ex.getMessage();
        }
        return "An unexpected error occurred: " + ex.getMessage();
    }
}
