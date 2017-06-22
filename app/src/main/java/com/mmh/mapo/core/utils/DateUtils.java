package com.mmh.mapo.core.utils;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Date utils
 */
public final class DateUtils {
	/**
	 * Defines RFC 822 date-time format.
	 */
	private static final String RFC_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	/**
	 * Parse date object to ISO 8601 string.
	 *
	 * @param milliseconds date object.
	 *
	 * @return formatted string.
	 */
	public static String getISO8601FromMilliseconds(Context context, long milliseconds) {
		DateTime dt = new DateTime(milliseconds)
				.withZoneRetainFields(DateTimeZone.UTC)
				.withZone(DateTimeZone.getDefault());
		return DateTimeFormat.forPattern(RFC_DATE_TIME_PATTERN)
				.print(dt);
	}

	/**
	 * Get timestamp from string date WITH TIMEZONE PARSED AS OFFSET
	 *
	 * @param date string containing date
	 *
	 * @return timestamp
	 */
	public static Long parseISO8601ToMilliseconds(String date) {
		return DateTimeFormat.forPattern(RFC_DATE_TIME_PATTERN)
				.withOffsetParsed()
				.parseDateTime(date)
				.toLocalDateTime().toDate().getTime();
	}

	//todo Android 7 support
	public static String getRegionalDate(Context context, Date date, String simpleDatePattern){
		final Locale locale = context.getResources().getConfiguration().locale;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2)
			simpleDatePattern = android.text.format.DateFormat.getBestDateTimePattern(locale, simpleDatePattern);
		return new SimpleDateFormat(simpleDatePattern, locale).format(date);
	}

	//todo Android 7 support
	public static String getRegionalTime(Context context, Date date, String simpleDatePattern){
		final Locale locale = context.getResources().getConfiguration().locale;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2)
			simpleDatePattern = android.text.format.DateFormat.getTimeFormat(context).format(date);
		else {
			simpleDatePattern = new SimpleDateFormat(simpleDatePattern, locale).format(date);
		}
		return simpleDatePattern;
	}
}
