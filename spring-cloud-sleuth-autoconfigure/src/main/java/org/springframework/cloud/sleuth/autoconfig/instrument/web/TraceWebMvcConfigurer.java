/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.autoconfig.instrument.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.web.mvc.SpanCustomizingAsyncHandlerInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC Adapter that adds the {@link SpanCustomizingAsyncHandlerInterceptor}.
 *
 * @author Marcin Grzejszczak
 * @since 1.0.3
 */
@Configuration(proxyBeanMethods = false)
@Import(SpanCustomizingAsyncHandlerInterceptor.class)
class TraceWebMvcConfigurer implements WebMvcConfigurer {

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.applicationContext.getBean(SpanCustomizingAsyncHandlerInterceptor.class));
	}

}
