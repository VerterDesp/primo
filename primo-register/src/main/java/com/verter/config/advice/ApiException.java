package com.verter.config.advice;

import java.util.List;

public record ApiException(List<String> errors) {

}
