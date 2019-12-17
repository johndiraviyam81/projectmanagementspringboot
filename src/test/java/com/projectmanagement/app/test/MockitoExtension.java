package com.projectmanagement.app.test;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * The Class MockitoExtension.
 */
public class MockitoExtension implements TestInstancePostProcessor, ParameterResolver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.junit.jupiter.api.extension.TestInstancePostProcessor#
	 * postProcessTestInstance(java.lang.Object,
	 * org.junit.jupiter.api.extension.ExtensionContext)
	 */
	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
		MockitoAnnotations.initMocks(testInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.junit.jupiter.api.extension.ParameterResolver#supportsParameter(org.junit
	 * .jupiter.api.extension.ParameterContext,
	 * org.junit.jupiter.api.extension.ExtensionContext)
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return parameterContext.getParameter().isAnnotationPresent(Mock.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.junit.jupiter.api.extension.ParameterResolver#resolveParameter(org.junit.
	 * jupiter.api.extension.ParameterContext,
	 * org.junit.jupiter.api.extension.ExtensionContext)
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return getMock(parameterContext.getParameter(), extensionContext);
	}

	/**
	 * Gets the mock.
	 *
	 * @param parameter
	 *            the parameter
	 * @param extensionContext
	 *            the extension context
	 * @return the mock
	 */
	private Object getMock(Parameter parameter, ExtensionContext extensionContext) {
		Class<?> mockType = parameter.getType();
		Store mocks = extensionContext.getStore(Namespace.create(MockitoExtension.class, mockType));
		String mockName = getMockName(parameter);

		if (mockName != null) {
			return mocks.getOrComputeIfAbsent(mockName, key -> mock(mockType, mockName));
		} else {
			return mocks.getOrComputeIfAbsent(mockType.getCanonicalName(), key -> mock(mockType));
		}
	}

	/**
	 * Gets the mock name.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the mock name
	 */
	private String getMockName(Parameter parameter) {
		String explicitMockName = parameter.getAnnotation(Mock.class).name().trim();
		if (!explicitMockName.isEmpty()) {
			return explicitMockName;
		} else if (parameter.isNamePresent()) {
			return parameter.getName();
		}
		return null;
	}
}
