
package com.online.exam.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUtil.
 */
public class StringUtil {
	
	/** The Constant ILLEGAL_SYMBOL_SET. */
	private static final Set<Character> ILLEGAL_SYMBOL_SET = getInvalidSymbolSet();
	
	/** The Constant ILLEGAL_SYMBOL_SET_EMAIL. */
	private static final Set<Character> ILLEGAL_SYMBOL_SET_EMAIL = getInvalidSymbolSetEmail();
	
	/** The Constant ILLEGAL_SYMBOL_SET_WEB. */
	private static final Set<Character> ILLEGAL_SYMBOL_SET_WEB = getInvalidSymbolSetWeb();
	
	/** The Constant SPLIT_STR. */
	private static final String SPLIT_STR = "@";

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(StringUtil.class);

	/**
	 * Check number.
	 * 
	 * @param sEntry
	 *            the s entry
	 * @param intLength
	 *            the int length
	 * @param floatLength
	 *            the float length
	 * @return true, if successful
	 * @throws AppException
	 *             the app exception
	 */
	public static boolean checkNumber(final String sEntry, final int intLength,
	        final int floatLength){
		boolean flag = false;
		if (null != sEntry) {
			try {
				if (sEntry.contains(".")) {
					final String intStr = sEntry.substring(0, sEntry.indexOf('.'));
					final String floatStr = sEntry.substring(sEntry.indexOf('.') + 1);
					if ((null != intStr) && (!"".equals(intStr)) && (intStr.length() <= intLength)
					        && (null != floatStr) && (!"".equals(floatStr))
					        && (floatStr.length() <= floatLength)) {
						flag = true;
					} else {
						LOG.error("The value is too large!");
					}
				} else {
					if (sEntry.length() <= intLength) {
						flag = true;
					} else {
						LOG.error("The value is too large!");
					}
				}

			} catch (Exception e) {
				// LOG.error(e.getMessage());
				LOG.error(e.getMessage(), e);
			}
		}
		return flag;
	}

	/**
	 * Text to html.
	 * 
	 * @param text
	 *            the text
	 * @return the string
	 */
	public static String textToHTML(final String text) {
		String result = null;
		if (null != text) {
			final int length = text.length();
			boolean prevSlashR = false;
			final StringBuffer out = new StringBuffer();
			for (int i = 0; i < length; i++) {
				final char ch = text.charAt(i);
				switch (ch) {
				case '\r':
					if (prevSlashR) {
						out.append("<br>");
					}
					prevSlashR = true;
					break;
				case '\n':
					prevSlashR = false;
					out.append("<br>");
					break;
				case '"':
					if (prevSlashR) {
						out.append("<br>");
						prevSlashR = false;
					}
					out.append("&quot;");
					break;
				case '<':
					if (prevSlashR) {
						out.append("<br>");
						prevSlashR = false;
					}
					out.append("&lt;");
					break;
				case '>':
					if (prevSlashR) {
						out.append("<br>");
						prevSlashR = false;
					}
					out.append("&gt;");
					break;
				case '&':
					if (prevSlashR) {
						out.append("<br>");
						prevSlashR = false;
					}
					out.append("&amp;");
					break;
				default:
					if (prevSlashR) {
						out.append("<br>");
						prevSlashR = false;
					}
					out.append(ch);
					break;
				}
			}
			result = out.toString();
		}
		return result;
	}

	/**
	 * Transfer html tag meaning.
	 * 
	 * @param value
	 *            Input String to Transfer
	 * @return Complete Transfer of String
	 */
	public static String htmlEncode(final String value) {
		final int length = value.length();
		final StringBuffer encodeValue = new StringBuffer(length + (length / 10));

		for (int i = 0; i < length; i++) {
			String nextCharacter = String.valueOf(value.charAt(i));

			if ("<".equals(nextCharacter)) {
				nextCharacter = "&lt;";
			}
			if (">".equals(nextCharacter)) {
				nextCharacter = "&gt;";
			}
			if ("\"".equals(nextCharacter)) {
				nextCharacter = "&quot;";
			}
			if ("&".equals(nextCharacter)) {
				nextCharacter = "&amp;";
			}
			if ("'".equals(nextCharacter)) {
				nextCharacter = "&#39;";
			}

			encodeValue.append(nextCharacter);
		}
		return encodeValue.toString();
	}

	/**
	 * Converter input Chinese charactor.
	 * 
	 * @param value
	 *            Input String to Converter
	 * @return Complete Converter of charactor
	 */
	public static String convertToUTF(final String value) {
		byte[] byte_value = null;
		String newValue = null;
		if (null != value) {
			try {
				byte_value = value.getBytes("ISO-8859-1");
				newValue = new String(byte_value, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				LOG.error(e.getMessage());
			}
		}
		return newValue;
	}

	/**
	 * Converter output Chinese charactor.
	 * 
	 * @param value
	 *            Input String to Converter
	 * @return Complete Converter of charactor
	 */
	public static String convertToISO(final String value) {
		String result = null;
		if (null != value) {
			byte[] byte_value = null;
			String newValue = "";
			try {
				byte_value = value.getBytes("UTF-8");
				newValue = new String(byte_value, "ISO-8859-1");

			} catch (UnsupportedEncodingException e) {
				LOG.error(e.getMessage());
			}
			result = newValue;
		}
		return result;
	}

	/**
	 * Handle newline on the textarea box.
	 * 
	 * @param value
	 *            Input String to Converter
	 * @return Complete Converter of charactor
	 */
	public static String formatOut(final String value) {
		final StringBuffer outValue = new StringBuffer();
		int Position = 0;
		while (true) {
			final int index = value.indexOf(0x0D, Position);
			if (index == -1) {
				break;
			}
			if (index > Position) {
				outValue.append(value.substring(Position, index));
			}
			outValue.append("<br>");
			Position = index + 1;
		}
		if (Position >= 0) {
			outValue.append(value.substring(Position));
		}
		return outValue.toString();

	}

	// Modification End Here.

	/**
	 * Gets the invalid symbol set.
	 * 
	 * @return Returns Invalid Symbol
	 */
	private static Set<Character> getInvalidSymbolSet() {
		/* Start PEN test bug Fix Jun 21, 2007 */
		/*
		 * char[] invalidSymbols = new char[] {'|', '&', ';', '$', '%', '@', '\'', '"', '<', '>', '(', ')', '+', '\n', '\r', ',', '\\'};
		 */
		final char[] invalidSymbols = new char[] { '!', '^', '|', '[', ']' };
		/* Start PEN test bug Fix Jun 21, 2007 */
		return chars2Set(invalidSymbols);
	}

	/**
	 * Gets the invalid symbol set email.
	 * 
	 * @return Returns Invalid Symbol for Email
	 */
	private static Set<Character> getInvalidSymbolSetEmail() {
		final char[] invalidSymbols = new char[] { '|', '&', ';', '$', '%', '@', '\'', '"', '<',
		        '>', '(', ')', '+', '\n', '\r', ',', '\\' };
		return chars2Set(invalidSymbols);
	}

	/**
	 * Gets the invalid symbol set web.
	 * 
	 * @return Returns Invalid Symbol for Web
	 */
	private static Set<Character> getInvalidSymbolSetWeb() {
		final char[] invalidSymbols = new char[] { '|', '&', ';', '$', '%', '@', '\'', '"', '<',
		        '>', '(', ')', '+', '\n', '\r', ',', '\\' };
		return chars2Set(invalidSymbols);
	}

	/**
	 * Convert chars to set.
	 * 
	 * @param chars
	 *            char[]
	 * @return set
	 */
	public static Set<Character> chars2Set(final char[] chars) {
		final Set<Character> set = new HashSet<Character>();
		if (null != chars) {
			for (int i = 0; i < chars.length; i++) {
				set.add(new Character(chars[i]));
			}
		}
		return set;

	}

	/**
	 * Validate input value is empty.
	 * 
	 * @param value
	 *            input String to Validate
	 * @return If value is null, return true; otherwise, return false
	 */
	public static boolean isNull(final String value) {
		return (null == value || value.trim().equals(""));
	}

	/**
	 * Validate input value is contains Non Digit Char.
	 * 
	 * @param value
	 *            input String to Validate
	 * @return If value is contains Non Digit Char, return true; otherwise, return false
	 */
	public static boolean containsNonDigitChar(final String value) {
		boolean result = false;
		if (!isNull(value)) {
			final int len = value.length();
			for (int i = 0; i < len; i++) {
				// We don't use Character.isDigit since it covers characters
				// other than '0'..'9'
				if (!isDigit(value.charAt(i))) {
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * Validate input value is contains Non Ascii Char.
	 * 
	 * @param value
	 *            input String to Validate
	 * @return If value is contains Non Digit Ascii Char, return true; otherwise, return false
	 */
	public static boolean containsNonAsciiChar(final String value) {
		boolean result = false;

		if (!isNull(value)) {
			final int len = value.length();
			for (int i = 0; i < len; i++) {
				if (value.charAt(i) > 127) {
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * Validate input value is contains Control Char.
	 * 
	 * @param value
	 *            input String to Validate
	 * @return If value is contains Control Char, return true; otherwise, return false
	 */
	public static boolean containsControlChar(final String value) {
		boolean result = false;
		if (!isNull(value)) {
			final int len = value.length();
			for (int i = 0; i < len; i++) {
				if (Character.isISOControl(value.charAt(i))) {
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * Validate input value is contains Illegal Symbol.
	 * 
	 * @param value
	 *            input String to Validate
	 * @return If value is contains Illegal Symbol, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbol(final String value) {
		return containsIllegalSymbol(value, null);
	}

	/**
	 * Validate input value is contains Illegal Symbol.
	 * 
	 * @param value
	 *            input String to Validate
	 * @param allowedIllegalSymbols
	 *            allowedIllegalSymbols
	 * @return If value is contains Illegal Symbol, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbol(final String value,
	        final Set<Character> allowedIllegalSymbols) {
		boolean result = false;
		if (null != value) {
			for (int i = 0; i < value.length(); i++) {
				final Character c = new Character(value.charAt(i));
				if (ILLEGAL_SYMBOL_SET.contains(c)
				        && (null == allowedIllegalSymbols || !allowedIllegalSymbols.contains(c))) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Validate input value is contains Illegal Symbol In Email.
	 * 
	 * @param value
	 *            input String to Validate
	 * @param allowedIllegalSymbols
	 *            allowedIllegalSymbols
	 * @return If value is contains Illegal Symbol In Email, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbolInEmail(final String value,
	        final Set<Character> allowedIllegalSymbols) {
		boolean result = false;
		if (null != value) {
			for (int i = 0; i < value.length(); i++) {
				final Character c = new Character(value.charAt(i));
				if (ILLEGAL_SYMBOL_SET_EMAIL.contains(c)
				        && (null == allowedIllegalSymbols || !allowedIllegalSymbols.contains(c))) {
					result = true;
				}
			}
		}
		return result;
	}

	/*
	 * added for penetration test issue - to check invalid character and then forward to error page
	 */
	/**
	 * Validate input value is contains Illegal Symbol In Web.
	 * 
	 * @param value
	 *            input String to Validate
	 * @param allowedIllegalSymbols
	 *            allowedIllegalSymbols
	 * @return If value is contains Illegal Symbol In Web, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbolInWeb(final String value,
	        final Set<Character> allowedIllegalSymbols) {
		boolean result = false;
		if (null != value) {
			for (int i = 0; i < value.length(); i++) {
				final Character c = new Character(value.charAt(i));
				if (ILLEGAL_SYMBOL_SET_WEB.contains(c)
				        && (null == allowedIllegalSymbols || !allowedIllegalSymbols.contains(c))) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Validate input value is Digit.
	 * 
	 * @param c
	 *            the c
	 * @return If value is Digit, return true; otherwise, return false
	 */
	public static boolean isDigit(final char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * Validate input value is Date.
	 * 
	 * @param date
	 *            Input String to Validate
	 * @param pattern
	 *            pattern
	 * @return If value is Date, return true; otherwise, return false
	 */
	public static boolean isDate(final String date, final String pattern) {
		boolean result = true;
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
			sdf.parse(date);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * Replace the string content.
	 * 
	 * @param paramStrSource
	 *            String Source
	 * @param strFrom
	 *            the characters from replace
	 * @param strTo
	 *            the characters to replace
	 * @return Replaced string
	 */
	public static String replace(final String paramStrSource, final String strFrom,
	        final String strTo) {
		String strSource = paramStrSource;
		final StringBuffer strDest = new StringBuffer("");
		final int intFromLen = strFrom.length();
		int intPos;

		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest.append(strSource.substring(0, intPos));
			strDest.append(strTo);
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest.append(strSource);

		return strDest.toString();
	}

	/**
	 * Replace the string content.
	 * 
	 * @param value
	 *            String Source
	 * @param from
	 *            the from
	 * @param to
	 *            the characters to replace
	 * @param ignoreCase
	 *            ignore Case(Lower Case)
	 * @return Replaced string
	 */
	public static String replaceAll(final String value, final String from, final String to,
	        final boolean ignoreCase) {
		String result = null;
		if (null != value) {
			final String activeValue = ignoreCase ? value.toLowerCase(Locale.ENGLISH) : value;
			final String fromValue = ignoreCase ? from.toLowerCase(Locale.ENGLISH) : from;

			final List<Integer> occurences = findOccurences(activeValue, fromValue);

			final StringBuffer buf = new StringBuffer();
			buf.append(value);
			int offset = 0;
			for (final Iterator<Integer> it = occurences.iterator(); it.hasNext();) {
				final Integer i = (Integer) it.next();
				final int index = i.intValue() + offset;
				buf.replace(index, index + from.length(), to);
				offset += to.length() - from.length();
			}
			result = buf.toString();
		}
		return result;
	}

	/**
	 * find an string in the string of position.
	 * 
	 * @param content
	 *            String Source
	 * @param matchValue
	 *            the characters from find
	 * @return find results
	 */
	private static List<Integer> findOccurences(final String content, final String matchValue) {
		final List<Integer> occurences = new LinkedList<Integer>();

		int pos = 0;
		while (pos < content.length()) {
			final int index = content.indexOf(matchValue, pos);
			if (index == -1) {
				break;
			}
			occurences.add(Integer.valueOf(String.valueOf(index)));
			pos = index + matchValue.length();
		}

		return occurences;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split.
	 * 
	 * @param line
	 *            line
	 * @param maxLength
	 *            maxLength
	 * @param splitters
	 *            splitters
	 * @return split of list
	 */
	public static List<String> wrap(final String line, final int maxLength, final char[] splitters) {
		final List<String> resultLines = new LinkedList<String>();
		String lineValue = line;
		while (true) {
			final String[] lines = wrapTo2Lines(lineValue, maxLength, splitters);
			resultLines.add(lines[0]);
			if (lines[1].length() == 0) {
				break;
			}
			lineValue = lines[1];
		}
		return resultLines;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split into two String.
	 * 
	 * @param line
	 *            line
	 * @param maxLength
	 *            maxLength
	 * @param splitters
	 *            splitters
	 * @return split of String
	 */
	private static String[] wrapTo2Lines(final String line, final int maxLength,
	        final char[] splitters) {
		String[] result = null;
		if (line.length() <= maxLength) {
			result = new String[] { line, "" };
		} else {
			final StringBuffer line1 = new StringBuffer();
			line1.append(line.substring(0, maxLength));
			String line2 = line.substring(maxLength);

			// Convert to Character Array
			final List<Character> splitterLst = new ArrayList<Character>();
			for (int i = 0; i < splitters.length; i++) {
				splitterLst.add(new Character(splitters[i]));
			}

			for (int k = 0; (k < line2.length() && splitterLst.contains(new Character(line2
			        .charAt(0)))); k++) {
				line1.append(line2.charAt(0));
				line2 = line2.substring(1);
			}

			// Calculate the cut-off character
			int maxIndex = -1;
			for (int i = 0; i < splitterLst.size(); i++) {
				final int index = line1.toString().substring(0, maxLength).lastIndexOf(
				        ((Character) splitterLst.get(i)).charValue());
				if (index > maxIndex) {
					maxIndex = index;
				}
			}

			if (maxIndex != -1) {
				line1.setLength(0);
				line1.append(line.substring(0, maxIndex + 1));
				line2 = line.substring(maxIndex + 1).trim();
			}
			result = new String[] { line1.toString(), line2 };
		}
		return result;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split.
	 * 
	 * @param line
	 *            line
	 * @param maxLength
	 *            maxLength
	 * @return split of list
	 */
	public static List<String> split(final String line, final int maxLength) {
		final List<String> resultLines = new LinkedList<String>();
		String lineValue = line;
		while (true) {
			final String[] lines = splitTo2Lines(lineValue, maxLength);
			resultLines.add(lines[0]);
			if (lines[1].length() == 0) {
				break;
			}
			lineValue = lines[1];
		}
		return resultLines;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split into two String.
	 * 
	 * @param line
	 *            line
	 * @param maxLength
	 *            maxLength
	 * @return split of String
	 */
	private static String[] splitTo2Lines(final String line, final int maxLength) {
		String[] result = null;
		if (line.length() <= maxLength) {
			result = new String[] { line, "" };
		} else {
			final String line1 = line.substring(0, maxLength);
			final String line2 = line.substring(maxLength);

			result = new String[] { line1, line2 };
		}
		return result;
	}

	/**
	 * According to the patterns Completed s is split.
	 * 
	 * @param s
	 *            line
	 * @param patterns
	 *            patterns
	 * @return split of list
	 */
	public static List<String> split(final String s, final String[] patterns) {
		final Set<String> checkedPatterns = new HashSet<String>();
		Arrays.sort(patterns);
		List<String> result = null;
		for (int i = patterns.length - 1; i >= 0; i--) {
			if (i == patterns.length - 1) {
				result = split(s, patterns[i], true);
			} else {
				int count = 0;
				while (true) {
					if (count == result.size()) {
						break;
					}
					final String temp = (String) result.get(count);
					if (checkedPatterns.contains(temp)) {
						count++;
					} else {
						final List<String> newResult = split(temp, patterns[i], true);
						if (newResult.size() == 1) {
							count++;
						} else {
							result.remove(count);
							result.addAll(count, newResult);
							count += newResult.size();
						}
					}
				}
			}
			checkedPatterns.add(patterns[i]);
		}
		return result;
	}

	/**
	 * According to the regex position in str,Completed str is split.
	 * 
	 * @param target
	 *            the target
	 * @param regex
	 *            regex
	 * @return split of list
	 */
	public static List<String> splitByTrim(final String target, final String regex) {
		final List<String> list = new ArrayList<String>();
		boolean start = true;

		String s = "";
		if (null != target) {
			s = target;
		}

		while (start) {
			s = s.trim();
			if (s.indexOf(regex) != -1) {
				final int beginIndex = s.indexOf(regex);
				list.add(s.substring(0, beginIndex));

				s = s.substring(beginIndex + 1).trim();
			} else {
				start = false;
				list.add(s);
			}
		}

		return list;
	}

	/**
	 * Split.
	 * 
	 * @param s
	 *            the s
	 * @param pattern
	 *            the pattern
	 * @return the list
	 */
	public static List<String> split(final String s, final String pattern) {
		return split(s, pattern, false);
	}

	/**
	 * According to the patterns Completed s is split.
	 * 
	 * @param s
	 *            line
	 * @param pattern
	 *            the pattern
	 * @param includePattern
	 *            includePattern
	 * @return split of list
	 */
	public static List<String> split(final String s, final String pattern,
	        final boolean includePattern) {
		final List<String> result = new LinkedList<String>();
		int pos = 0;
		while (true) {
			if (pos == s.length()) {
				break;
			}

			final int index = s.indexOf(pattern, pos);
			if (index == -1) {
				result.add(s.substring(pos, s.length()));
				break;
			} else {
				result.add(s.substring(pos, index));
				if (includePattern) {
					result.add(s.substring(index, index + pattern.length()));
				}
				pos = index + pattern.length();
			}
		}
		return result;
	}

	/**
	 * If the line for more than Length,According to the Length Completed line is split.
	 * 
	 * @param line
	 *            line
	 * @param length
	 *            length
	 * @param checkEmpty
	 *            checkEmpty
	 * @return split of list
	 */
	public static List<String> splitWithFixedLength(final String line, final int length,
	        final boolean checkEmpty) {
		final List<String> result = new ArrayList<String>();
		if (null != line) {
			int iStart = 0, iEnd = 0;
			final int iLength = line.length();
			String sCode = "";
			while (iStart < iLength) {
				iEnd = (iStart + length < iLength) ? iStart + length : iLength;
				sCode = line.substring(iStart, iEnd);
				if (!checkEmpty || !isNull(sCode)) {
					result.add(sCode);
				}
				iStart += length;
			}
		}
		return result;
	}

	/**
	 * If the target length for less than maxLength length,On the right complement pad.
	 * 
	 * @param target
	 *            target
	 * @param pad
	 *            pad
	 * @param maxLength
	 *            maxLength
	 * @return To meet the requirements of a string
	 */
	public static String rightPad(final String target, final String pad, final int maxLength) {
		final StringBuffer targetValue = new StringBuffer();
		targetValue.append(null == target ? "" : target);

		while (true) {
			if (targetValue.length() < maxLength) {
				targetValue.append(pad);
			} else {
				break;
			}
		}
		return targetValue.toString();
	}

	/**
	 * If the target length for less than maxLength length,On the left complement pad.
	 * 
	 * @param target
	 *            target
	 * @param pad
	 *            pad
	 * @param maxLength
	 *            maxLength
	 * @return To meet the requirements of a string
	 */
	public static String leftPad(final String target, final String pad, final int maxLength) {
		final StringBuffer targetValue = new StringBuffer();
		targetValue.append(null == target ? "" : target);

		while (true) {
			if (targetValue.length() < maxLength) {
				final String s = pad + targetValue.toString();
				targetValue.setLength(0);
				targetValue.append(s);
			} else {
				break;
			}
		}
		return targetValue.toString();
	}

	/**
	 * Convert String[] to string.
	 * 
	 * @param string
	 *            target
	 * @param pattern
	 *            pad
	 * @return String
	 */
	public static String getListString(final String[] string, final String pattern) {
		final StringBuffer sListString = new StringBuffer();
		final int length = string.length;
		for (int i = 0; i < length; i++) {
			if (!"".equals(sListString.toString())) {
				sListString.append(',');
			}
			sListString.append(pattern);
			sListString.append(string[i]);
			sListString.append(pattern);
		}
		return sListString.toString();
	}

	/**
	 * Convert byte[] to Hex String.
	 * 
	 * @param ba
	 *            byte[]
	 * @return Hex String
	 */
	public static String toHex(final byte[] ba) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ba.length; i++) {
			final int b = (int) ba[i];
			sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3) + " ");
		}
		return sb.toString();
	}

	/**
	 * Convert String to "UTF-8" type.then Compare length of input String and input criteria
	 * 
	 * @param sEntry
	 *            sEntry
	 * @param iLength
	 *            Target length
	 * @return If sEntry length than iLength, return true; otherwise, return false
	 */
	public static boolean checkLength(final String sEntry, final int iLength) {
		boolean result = false;
		if (null != sEntry) {
			byte[] byte1 = null;
			try {
				byte1 = sEntry.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				LOG.error(e.getMessage());
			}
			// if (sEntry.length() <= iLength)
			if (byte1.length > iLength) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * find an string in the string of Frequencies.
	 * 
	 * @param inputString
	 *            String Source
	 * @param findString
	 *            the characters from find
	 * @return Frequencies
	 */
	public static int findNumberOfOccurrence(final String inputString, final String findString) {
		int count = 0;
		int pos = 0;
		int prevpos = 0;
		while (pos != -1) {
			pos = inputString.indexOf(findString, prevpos);
			if (pos != -1) {
				count++;
				prevpos = pos + findString.length();
			}
		}
		return count;
	}

	/**
	 * Convert String to "UTF-8" type.then Compare length of input String and Convert String is consistent
	 * 
	 * @param inputString
	 *            String Source
	 * @return If Compare length of input String and Convert String is consistent, return true; otherwise, return false
	 */
	public static boolean checkForDoubleByteChar(final String inputString) {
		boolean result = false;
		if (null == inputString || inputString.equals("")) {
			result = false;
		} else {
			try {
				result = (inputString.getBytes("utf-8").length != inputString.length());
			} catch (UnsupportedEncodingException e) {
				LOG.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * Compiles the given regular expression and attempts to match the given input against it.
	 * 
	 * @param inputString
	 *            character sequence to be matched
	 * @param pattern
	 *            The The expression to be compiled
	 * @return If Compare length of input String and Convert String is consistent, return true; otherwise, return false
	 */
	public static boolean checkStringPattern(final String inputString, final String pattern) {
		boolean result = true;
		// String regex = "[A-Z ]{2}[0-9]{6}[0-9A]{1}";
		if (null == inputString || null == pattern) {
			result = false;
		} else {
			result = Pattern.matches(pattern, inputString);
		}
		return result;
	}

	/**
	 * Gets the string array by str.
	 * 
	 * @param parmStr
	 *            the parm str
	 * @param parmSplitStr
	 *            the parm split str
	 * @return the string array by str
	 */
	public static String[] getStringArrayByStr(final String parmStr, final String parmSplitStr) {
		String str = parmStr.replaceAll(parmSplitStr, SPLIT_STR);
		while (true) {
			final int beginLength = str.length();
			str = str.replaceAll(SPLIT_STR + SPLIT_STR, SPLIT_STR + " " + SPLIT_STR);
			final int endLength = str.length();
			if (beginLength == endLength) {
				break;
			}
		}
		final String lastStr = str.substring(str.length() - 1);
		if (SPLIT_STR.equals(lastStr)) {
			str += " ";
		}

		return str.split(SPLIT_STR);
	}

	/**
	 * substring string as length.
	 * 
	 * @param inputString
	 *            the input string
	 * @param len
	 *            the len
	 * @return the string
	 */
	public static String subStr(final String inputString, final int len) {
		String s = inputString;
		if (s.length() > len) {
			s = s.substring(0, len);
		}
		return s;
	}

	/**
	 * strip html.
	 * 
	 * @param content
	 *            the content
	 * @return the string
	 */
	public static String stripHtml(final String content) {
		String s = content;
		// <p>
		s = s.replaceAll("<p.*?>", "\r\n");
		// <br>
		s = s.replaceAll("<br\\s*/?>", "\r\n");
		// other html sign
		s = s.replaceAll("<.*?>", "");

		return s;
	}

	/**
	 * Check is decimal.
	 * 
	 * @param number
	 *            the number
	 * @return true, if successful
	 */
	public static boolean checkIsDecimal(final String number) {
		boolean result = true;
		String number1 = "";
		if ((null == number) || ("".equals(number.trim()))) {
			result = false;
		} else {
			final int indexFu = number.indexOf('.');
			if (indexFu > 0) {
				result = false;
			} else if (indexFu == 0) {
				number1 = number.substring(1);
				final int index = number1.indexOf('.');
				if (index < 0) {
					result = isNumeric(number1);
				} else {
					final String num1 = number1.substring(0, index);
					final String num2 = number1.substring(index + 1);
					result = isNumeric(num1) && isNumeric(num2);
				}
			}
		}
		return result;

	}

	/**
	 * Checks if is numeric.
	 * 
	 * @param str
	 *            the str
	 * @return true, if is numeric
	 */
	public static boolean isNumeric(final String str) {
		boolean result = true;
		if (null == str) {
			result = false;
		} else {
			final int sz = str.length();
			for (int i = 0; i < sz; i++) {
				if (!Character.isDigit(str.charAt(i))) {
					result = false;
				}
			}
		}
		return result;
	}

}