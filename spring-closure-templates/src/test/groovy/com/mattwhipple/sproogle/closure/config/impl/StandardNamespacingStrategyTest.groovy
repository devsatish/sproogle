package com.mattwhipple.sproogle.closure.config.impl;

import spock.lang.Specification

class StandardNamespacingStrategyTest extends Specification {

	def 'applies provided default namespace if View does not contain a period, merging dots'(namespace, templateName, expected) {
		expect:
		new StandardNamespacingStrategy(namespace).applyNamespace(templateName) == expected
		
		where:
		namespace				| templateName				| expected
		"org.sproogle"			| "test"					| "org.sproogle.test"
		"org.sproogle."			| "test"					| "org.sproogle.test"
		"org.sproogle"			| ".test"					| "org.sproogle.test"
		"org.sproogle."			| ".test"					| "org.sproogle.test"
		"org.sproogle"			| "org.test"				| "org.test"
		"org.sproogle"			| "test."					| "test."
		"org.sproogle"			| "."						| "org.sproogle."
	}

}
