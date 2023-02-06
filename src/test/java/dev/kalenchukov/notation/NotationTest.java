/*
 * Copyright © 2022-2023 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.kalenchukov.notation;

import dev.kalenchukov.notation.resources.NotationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NotationTest
{
	private static final String[] UPPER_CASE = {
		"HELLO", "HELLO_WORLD", "HELLO_WORLD_MATRIX", "HELLO_WORLD1", "__HELLO_WORLD_MATRIX", "_HELLO_"
	};

	private static final String[] CAMEL_CASE = {
		"hello", "helloWorld", "helloWorldMatrix", "helloWorld1", "__helloWorldMatrix", "_hello_"
	};

	private static final String[] KEBAB_CASE = {
		"hello", "hello-world", "hello-world-matrix", "hello-world1", "__hello-world-matrix", "_hello_"
	};

	private static final String[] SNAKE_CASE = {
		"hello", "hello_world", "hello_world_matrix", "hello_world1", "__hello_world_matrix", "_hello_"
	};

	private static final String[] PASCAL_CASE = {
		"Hello", "HelloWorld", "HelloWorldMatrix", "HelloWorld1", "__HelloWorldMatrix", "_Hello_"
	};

	@ParameterizedTest
	@ValueSource(strings = {
		"HELLO", "HELLO_WORLD", "HELLO_WORLD_MATRIX", "HELLO_WORLD1",
		"HEL1LO_000_WORLD1", "_HELLO_WORLD_1"
	})
	public void testIsUpperCase(String value)
	{
		assertTrue(Notation.isUpperCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"", "_", "__", "0", "0123456789", "0_12345678_9", "_0_0_", "0_0",
		"hello_world", "HELLo_WORLD", "HELLO_WORLd", "h_WORLD1",
		"HELLO__WORLD", "__HELLO_WORLD_MATRIX", "HELLO_WORLD1__", "HELLO_123__",
		"HELLO____WORLD", "____HELLO_WORLD_MATRIX", "HELLO_WORLD1____", "HELLO_123____"
	})
	public void testIsUpperCaseNotCorrect(String value)
	{
		assertFalse(Notation.isUpperCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"hello", "hello-world", "hello-world-matrix",
		"hello-world1", "hel1lo-000-world1", "-hello-world-1"
	})
	public void testIsKebabCase(String value)
	{
		assertTrue(Notation.isKebabCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"", "-", "--", "0", "0123456789", "0-12345678-9", "-0-0-", "0-0",
		"HELLO-WORLD", "hellO-world", "hello-worlD", "H-world1",
		"hello--world", "--hello-world-matrix", "hello-world1--", "hello-123--",
		"hello----world", "----hello-world-matrix", "hello-world1----", "hello-123----"
	})
	public void testIsKebabCaseNotCorrect(String value)
	{
		assertFalse(Notation.isKebabCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"hello", "hello_world", "hello_world_matrix",
		"hello_world1", "hel1lo_000_world1", "_hello_world_1"
	})
	public void testIsSnakeCase(String value)
	{
		assertTrue(Notation.isSnakeCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"", "_", "__", "0", "0123456789", "0_12345678_9", "_0_0_", "0_0",
		"HELLO_WORLD", "hellO_world", "hello_worlD", "H_world1",
		"hello__world", "__hello_world_matrix", "hello_world1__", "hello_123__",
		"hello____world", "____hello_world_matrix", "hello_world1____", "hello_123____"
	})
	public void testIsSnakeCaseNotCorrect(String value)
	{
		assertFalse(Notation.isSnakeCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"hello", "helloWorld", "helloWorldMatrix", "helloWorld1",
		"hel1lo000World1", "helloWorld1", "hellOWorld", "helloWorlD"
	})
	public void testIsCamelCase(String value)
	{
		assertTrue(Notation.isCamelCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"", "H", "HH", "0", "0123456789", "0H12345678H9", "H0H0H", "0H0",
		"HELLOWORLD", "HWorld1",
		"HelloWorld", "HHelloWorldMatrix", "HelloWorld1", "Hello123"
	})
	public void testIsCamelCaseNotCorrect(String value)
	{
		assertFalse(Notation.isCamelCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"Hello", "HelloWorld", "HelloWorldMatrix", "HelloWorld1",
		"Hel1lo000World1", "HelloWorld1", "HellOWorld", "HelloWorlD"
	})
	public void testIsPascalCase(String value)
	{
		assertTrue(Notation.isPascalCase(value));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"", "h", "hh", "0", "0123456789", "0h12345678h9", "h0h0h", "h0h0h", "0h0",
		"hELLOwORLD", "hWorld1",
		"helloworld", "hhelloWorldMatrix", "helloWorld1", "hello123"
	})
	public void testIsPascalCaseNotCorrect(String value)
	{
		assertFalse(Notation.isPascalCase(value));
	}

	@Test
	public void testToNotationTypeUpperCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(UPPER_CASE[i], Notation.to(UPPER_CASE[i], NotationType.UPPER_CASE));
			assertEquals(UPPER_CASE[i], Notation.to(CAMEL_CASE[i], NotationType.UPPER_CASE));
			assertEquals(UPPER_CASE[i], Notation.to(KEBAB_CASE[i], NotationType.UPPER_CASE));
			assertEquals(UPPER_CASE[i], Notation.to(SNAKE_CASE[i], NotationType.UPPER_CASE));
			assertEquals(UPPER_CASE[i], Notation.to(PASCAL_CASE[i], NotationType.UPPER_CASE));
		}
	}

	@Test
	public void testToNotationTypeKebabCase()
	{
		for (int i = 0; i < KEBAB_CASE.length; i++)
		{
			assertEquals(KEBAB_CASE[i], Notation.to(UPPER_CASE[i], NotationType.KEBAB_CASE));
			assertEquals(KEBAB_CASE[i], Notation.to(CAMEL_CASE[i], NotationType.KEBAB_CASE));
			assertEquals(KEBAB_CASE[i], Notation.to(KEBAB_CASE[i], NotationType.KEBAB_CASE));
			assertEquals(KEBAB_CASE[i], Notation.to(SNAKE_CASE[i], NotationType.KEBAB_CASE));
			assertEquals(KEBAB_CASE[i], Notation.to(PASCAL_CASE[i], NotationType.KEBAB_CASE));
		}
	}

	@Test
	public void testToNotationTypeSnakeCase()
	{
		for (int i = 0; i < SNAKE_CASE.length; i++)
		{
			assertEquals(SNAKE_CASE[i], Notation.to(UPPER_CASE[i], NotationType.SNAKE_CASE));
			assertEquals(SNAKE_CASE[i], Notation.to(CAMEL_CASE[i], NotationType.SNAKE_CASE));
			assertEquals(SNAKE_CASE[i], Notation.to(KEBAB_CASE[i], NotationType.SNAKE_CASE));
			assertEquals(SNAKE_CASE[i], Notation.to(SNAKE_CASE[i], NotationType.SNAKE_CASE));
			assertEquals(SNAKE_CASE[i], Notation.to(PASCAL_CASE[i], NotationType.SNAKE_CASE));
		}
	}

	@Test
	public void testToNotationTypeCamelCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(CAMEL_CASE[i], Notation.to(UPPER_CASE[i], NotationType.CAMEL_CASE));
			assertEquals(CAMEL_CASE[i], Notation.to(CAMEL_CASE[i], NotationType.CAMEL_CASE));
			assertEquals(CAMEL_CASE[i], Notation.to(KEBAB_CASE[i], NotationType.CAMEL_CASE));
			assertEquals(CAMEL_CASE[i], Notation.to(SNAKE_CASE[i], NotationType.CAMEL_CASE));
			assertEquals(CAMEL_CASE[i], Notation.to(PASCAL_CASE[i], NotationType.CAMEL_CASE));
		}
	}

	@Test
	public void testToNotationTypePascalCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(PASCAL_CASE[i], Notation.to(UPPER_CASE[i], NotationType.PASCAL_CASE));
			assertEquals(PASCAL_CASE[i], Notation.to(CAMEL_CASE[i], NotationType.PASCAL_CASE));
			assertEquals(PASCAL_CASE[i], Notation.to(KEBAB_CASE[i], NotationType.PASCAL_CASE));
			assertEquals(PASCAL_CASE[i], Notation.to(SNAKE_CASE[i], NotationType.PASCAL_CASE));
			assertEquals(PASCAL_CASE[i], Notation.to(PASCAL_CASE[i], NotationType.PASCAL_CASE));
		}
	}

	@Test
	public void testToUpperCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(UPPER_CASE[i], Notation.toUpperCase(UPPER_CASE[i]));
			assertEquals(UPPER_CASE[i], Notation.toUpperCase(CAMEL_CASE[i]));
			assertEquals(UPPER_CASE[i], Notation.toUpperCase(KEBAB_CASE[i]));
			assertEquals(UPPER_CASE[i], Notation.toUpperCase(SNAKE_CASE[i]));
			assertEquals(UPPER_CASE[i], Notation.toUpperCase(PASCAL_CASE[i]));
		}
	}

	@Test
	public void testToKebabCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(KEBAB_CASE[i], Notation.toKebabCase(UPPER_CASE[i]));
			assertEquals(KEBAB_CASE[i], Notation.toKebabCase(CAMEL_CASE[i]));
			assertEquals(KEBAB_CASE[i], Notation.toKebabCase(KEBAB_CASE[i]));
			assertEquals(KEBAB_CASE[i], Notation.toKebabCase(SNAKE_CASE[i]));
			assertEquals(KEBAB_CASE[i], Notation.toKebabCase(PASCAL_CASE[i]));
		}
	}

	@Test
	public void testToSnakeCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(SNAKE_CASE[i], Notation.toSnakeCase(UPPER_CASE[i]));
			assertEquals(SNAKE_CASE[i], Notation.toSnakeCase(CAMEL_CASE[i]));
			assertEquals(SNAKE_CASE[i], Notation.toSnakeCase(KEBAB_CASE[i]));
			assertEquals(SNAKE_CASE[i], Notation.toSnakeCase(SNAKE_CASE[i]));
			assertEquals(SNAKE_CASE[i], Notation.toSnakeCase(PASCAL_CASE[i]));
		}
	}

	@Test
	public void testToCamelCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(CAMEL_CASE[i], Notation.toCamelCase(UPPER_CASE[i]));
			assertEquals(CAMEL_CASE[i], Notation.toCamelCase(CAMEL_CASE[i]));
			assertEquals(CAMEL_CASE[i], Notation.toCamelCase(KEBAB_CASE[i]));
			assertEquals(CAMEL_CASE[i], Notation.toCamelCase(SNAKE_CASE[i]));
			assertEquals(CAMEL_CASE[i], Notation.toCamelCase(PASCAL_CASE[i]));
		}
	}

	@Test
	public void testToPascalCase()
	{
		for (int i = 0; i < UPPER_CASE.length; i++)
		{
			assertEquals(PASCAL_CASE[i], Notation.toPascalCase(UPPER_CASE[i]));
			assertEquals(PASCAL_CASE[i], Notation.toPascalCase(CAMEL_CASE[i]));
			assertEquals(PASCAL_CASE[i], Notation.toPascalCase(KEBAB_CASE[i]));
			assertEquals(PASCAL_CASE[i], Notation.toPascalCase(SNAKE_CASE[i]));
			assertEquals(PASCAL_CASE[i], Notation.toPascalCase(PASCAL_CASE[i]));
		}
	}
}