package com.erayucar.myweatherapp.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.erayucar.myweatherapp.domain.model.toFormatUrl
import com.erayucar.myweatherapp.presentation.Screen
import com.erayucar.myweatherapp.presentation.state.WeatherEvent
import com.erayucar.myweatherapp.presentation.state.WeatherState
import com.erayucar.myweatherapp.presentation.viewmodel.WeatherViewModel
import java.time.format.DateTimeFormatter


@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navController: NavController
) {
    println("hello hello")

    var state: WeatherState = viewModel.weatherState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF38A5D6))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xFFFFFFFF))
        } else if (state.error.isNotEmpty()) {
            Text(text = state.error)
        } else {
            println("hello hello 2")
            WeatherSearchBar(modifier = Modifier
                .fillMaxWidth(),
                hint = "Search", onSearch = {
                    viewModel.onEvent(WeatherEvent.GetWeatherForCity(it))
                    state = WeatherState(isLoading = true)
                })
            WeatherCard(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),

                navController = navController
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCard(
    state: WeatherState,
    modifier: Modifier,
    navController: NavController
) {
    state.weatherInfo?.let { data ->
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = data.city,
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFFFFFFF),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .padding(10.dp),

            colors = CardDefaults.cardColors(containerColor = Color(0x4DFFFFFF)),


            onClick = {
                navController.navigate(Screen.weatherDeatilScreen.route + "/" + data.city)
            }
        ) {
            Column(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                LoadImage(path = data.toFormatUrl(data.icon))
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = data.condition,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${data.temperature}°C",
                    fontSize = 50.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Text(
                        text = "humidity : %" + data.humidity.toString(),
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF)
                    )
                    Text(text = "visibility: " + data.visibility.toString() + " km", fontSize = 16.sp, color = Color(0xFFFFFFFF))

                }

            }

        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(path: String) {
    Image(
        painter = rememberAsyncImagePainter(model = path),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
    )


    // GlideImage(model = path, contentDescription = "loadimage")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchBar(
    modifier: Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text = remember {
        mutableStateOf("")
    }
    var isHintDisplayed = remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        TextField(
            value = text.value,
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text.value)
            }),
            onValueChange = {
                text.value = it
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isHintDisplayed.value = it.isFocused != true && text.value.isEmpty()
                }
        )
        if (isHintDisplayed.value) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}