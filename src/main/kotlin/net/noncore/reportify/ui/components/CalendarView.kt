package net.noncore.reportify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarView(
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var currentMonth by remember { mutableStateOf(YearMonth.from(selectedDate)) }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // 月のナビゲーション
        MonthNavigation(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 曜日ヘッダー
        WeekdayHeader()

        Spacer(modifier = Modifier.height(8.dp))

        // カレンダーグリッド
        CalendarGrid(
            yearMonth = currentMonth,
            selectedDate = selectedDate,
            onDateSelected = onDateSelected,
        )
    }
}

@Composable
private fun MonthNavigation(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "前の月")
        }

        Text(
            text = "${currentMonth.year}年${currentMonth.monthValue}月",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )

        IconButton(onClick = onNextMonth) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "次の月")
        }
    }
}

@Composable
private fun WeekdayHeader() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        val weekdays = listOf("日", "月", "火", "水", "木", "金", "土")
        weekdays.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color =
                    if (day == "日") {
                        Color.Red
                    } else if (day == "土") {
                        Color.Blue
                    } else {
                        Color.Unspecified
                    },
            )
        }
    }
}

@Composable
private fun CalendarGrid(
    yearMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
) {
    val calendarDays = remember(yearMonth) { generateCalendarDays(yearMonth) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(calendarDays) { day ->
            CalendarDay(
                day = day,
                isSelected = day.date == selectedDate,
                isCurrentMonth = day.isCurrentMonth,
                onDateSelected = onDateSelected,
            )
        }
    }
}

@Composable
private fun CalendarDay(
    day: CalendarDay,
    isSelected: Boolean,
    isCurrentMonth: Boolean,
    onDateSelected: (LocalDate) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .aspectRatio(1f)
                .background(
                    when {
                        isSelected -> MaterialTheme.colorScheme.primaryContainer
                        else -> Color.Transparent
                    },
                ).clickable { onDateSelected(day.date) },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color =
                when {
                    isSelected -> MaterialTheme.colorScheme.onPrimaryContainer
                    !isCurrentMonth -> Color.Gray
                    day.date.dayOfWeek == DayOfWeek.SUNDAY -> Color.Red
                    day.date.dayOfWeek == DayOfWeek.SATURDAY -> Color.Blue
                    else -> MaterialTheme.colorScheme.onSurface
                },
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}

private data class CalendarDay(
    val date: LocalDate,
    val isCurrentMonth: Boolean,
)

private fun generateCalendarDays(yearMonth: YearMonth): List<CalendarDay> {
    val firstDayOfMonth = yearMonth.atDay(1)
    val lastDayOfMonth = yearMonth.atEndOfMonth()

    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // 日曜日を0にする
    val daysInMonth = lastDayOfMonth.dayOfMonth

    val calendarDays = mutableListOf<CalendarDay>()

    // 前月の日付を追加
    repeat(firstDayOfWeek) {
        val previousDate = firstDayOfMonth.minusDays((firstDayOfWeek - it).toLong())
        calendarDays.add(CalendarDay(previousDate, false))
    }

    // 当月の日付を追加
    for (day in 1..daysInMonth) {
        val date = yearMonth.atDay(day)
        calendarDays.add(CalendarDay(date, true))
    }

    // 次月の日付を追加（6週間分になるように）
    val remainingDays = 42 - calendarDays.size
    for (day in 1..remainingDays) {
        val nextDate = lastDayOfMonth.plusDays(day.toLong())
        calendarDays.add(CalendarDay(nextDate, false))
    }

    return calendarDays
}
