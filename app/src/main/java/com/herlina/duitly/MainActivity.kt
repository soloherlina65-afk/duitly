package com.herlina.duitly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

private val Green = Color(0xFF078B55)
private val DarkGreen = Color(0xFF075C3C)
private val LightGreen = Color(0xFFE3F8ED)
private val Background = Color(0xFFF7F9F8)
private val DarkText = Color(0xFF17231E)
private val GrayText = Color(0xFF858D89)
private val Red = Color(0xFFE75C62)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Green,
                    background = Background,
                    surface = Color.White
                )
            ) {
                DuitlyDashboard()
            }
        }
    }
}

@Composable
fun DuitlyDashboard() {
    Scaffold(
        containerColor = Background,
        bottomBar = { BottomNavigation() }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(18.dp))

            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("▣", fontSize = 30.sp, color = Green)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Duitly",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGreen
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("♧", fontSize = 25.sp)
                    Spacer(modifier = Modifier.width(15.dp))
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(LightGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("A", color = Green, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // TOTAL SALDO
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Green
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                listOf(DarkGreen, Green)
                            )
                        )
                        .padding(22.dp)
                ) {
                    Column {
                        Text(
                            "Total Saldo  ◉",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            "Rp 4.250.000",
                            fontSize = 29.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "↑  Kondisi keuangan bulan ini",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // PEMASUKAN DAN PENGELUARAN
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MoneyCard(
                    title = "Pemasukan",
                    amount = "Rp 5.000.000",
                    symbol = "↑",
                    color = LightGreen,
                    modifier = Modifier.weight(1f)
                )

                MoneyCard(
                    title = "Pengeluaran",
                    amount = "Rp 750.000",
                    symbol = "↓",
                    color = Color(0xFFFFECEE),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // GRAFIK
            SectionTitle(
                title = "Grafik Keuangan",
                action = "Lihat Detail"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Pemasukan 30 hari terakhir",
                        color = GrayText,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    FinanceChart()

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")
                            .forEach {
                                Text(
                                    it,
                                    color = GrayText,
                                    fontSize = 11.sp
                                )
                            }
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // KATEGORI
            SectionTitle(
                title = "Kategori Teratas",
                action = "Lihat Semua"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                CategoryCard(
                    emoji = "🍜",
                    name = "Makanan",
                    percentage = "32%",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFEEEE)
                )

                CategoryCard(
                    emoji = "🚗",
                    name = "Transportasi",
                    percentage = "24%",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFEDF4FF)
                )

                CategoryCard(
                    emoji = "🛍️",
                    name = "Belanja",
                    percentage = "20%",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFF3E3)
                )

                CategoryCard(
                    emoji = "💚",
                    name = "Lainnya",
                    percentage = "24%",
                    modifier = Modifier.weight(1f),
                    color = LightGreen
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // TRANSAKSI TERBARU
            SectionTitle(
                title = "Transaksi Terbaru",
                action = "Lihat Semua"
            )

            Spacer(modifier = Modifier.height(12.dp))

            TransactionItem(
                emoji = "💼",
                title = "Gaji Bulanan",
                subtitle = "Hari ini • 08.00",
                amount = "+ Rp 5.000.000",
                isIncome = true
            )

            TransactionItem(
                emoji = "🍜",
                title = "Makan Siang",
                subtitle = "Hari ini • 12.30",
                amount = "- Rp 30.000",
                isIncome = false
            )

            TransactionItem(
                emoji = "🛒",
                title = "Belanja Bulanan",
                subtitle = "Kemarin • 15.00",
                amount = "- Rp 150.000",
                isIncome = false
            )

            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

// KARTU PEMASUKAN DAN PENGELUARAN
@Composable
fun MoneyCard(
    title: String,
    amount: String,
    symbol: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    symbol,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green
                )

                Spacer(modifier = Modifier.width(7.dp))

                Text(
                    title,
                    fontSize = 12.sp,
                    color = DarkText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                amount,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
        }
    }
}

// JUDUL SECTION
@Composable
fun SectionTitle(
    title: String,
    action: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Text(
            action + "  ›",
            color = Green,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// GRAFIK KEUANGAN
@Composable
fun FinanceChart() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        val points = listOf(
            0.72f, 0.43f, 0.62f,
            0.25f, 0.53f, 0.20f, 0.34f
        )

        val stepX = size.width / (points.size - 1)

        // Garis horizontal
        for (i in 0..3) {
            val y = size.height * i / 3
            drawLine(
                color = Color(0xFFEAF0EC),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )
        }

        val path = Path()

        points.forEachIndexed { index, value ->
            val x = index * stepX
            val y = value * size.height

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)

            }
        }

        drawPath(
            path = path,
            color = Green,
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = 3.dp.toPx()
            )
        )

        points.forEachIndexed { index, value ->
            drawCircle(
                color = Green,
                radius = 4.dp.toPx(),
                center = Offset(
                    index * stepX,
                    value * size.height
                )
            )
        }
    }
}

// KARTU KATEGORI
@Composable
fun CategoryCard(
    emoji: String,
    name: String,
    percentage: String,
    modifier: Modifier = Modifier,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp, horizontal = 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emoji, fontSize = 22.sp)

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                name,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = DarkText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                percentage,
                fontSize = 11.sp,
                color = Green,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ITEM TRANSAKSI
@Composable
fun TransactionItem(
    emoji: String,
    title: String,
    subtitle: String,
    amount: String,
    isIncome: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(
                    if (isIncome) LightGreen
                    else Color(0xFFFFECEE)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 21.sp)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )

            Text(
                subtitle,
                fontSize = 11.sp,
                color = GrayText
            )
        }

        Text(
            amount,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isIncome) Green else Red
        )
    }

    HorizontalDivider(color = Color(0xFFEFF1EF))
}

// NAVIGASI BAWAH
@Composable
fun BottomNavigation() {
    Surface(
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(65.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem("⌂", "Beranda", true)
            NavigationItem("▤", "Transaksi", false)

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Green),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "+",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }

            NavigationItem("▣", "Tabungan", false)
            NavigationItem("♙", "Akun", false)
        }
    }
}

@Composable
fun NavigationItem(
    icon: String,
    label: String,
    selected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            icon,
            fontSize = 21.sp,
            color = if (selected) Green else GrayText
        )

        Text(
            label,
            fontSize = 10.sp,
            color = if (selected) Green else GrayText,
            fontWeight = if (selected) FontWeight.Bold
            else FontWeight.Normal
        )
    }
}

// PREVIEW ANDROID STUDIO
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DuitlyDashboardPreview() {
    MaterialTheme {
        DuitlyDashboard()
    }
}
