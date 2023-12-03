package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp(Modifier.fillMaxSize())
                }
            }
        }
    }
}

/**
 * Funcion principal que tiene toda la estructura de la aplicación
 */
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    MainColumn(modifier.fillMaxSize())



    
}

/**
 * Función encargada de pintar la columna y sus elementos
 */
@Composable
fun MainColumn(modifier:Modifier = Modifier){
    var resultado by remember {
        mutableStateOf(1)
    }

    //Establece el número de clicks necesarios para cambiar de imagen e inicializa la variable
    //click para contar el número de clicks que hace el usuario
    var click = 0
    var numeroClicks = (2..4).random()

    //Los textos, imagenes y descripción se asignan en función de la "etapa" en la que esté
    //la aplicación

    val imageResource = when(resultado){
        1 -> R.drawable.lemon_tree
        2-> R.drawable.lemon_squeeze
        3-> R.drawable.lemon_drink
        4-> R.drawable.lemon_restart
        else -> R.drawable.lemon_squeeze
    }

    val stringResource = when(resultado){

        1 -> R.string.selectLemon
        2-> R.string.tapLemon
        3-> R.string.tapLemonade
        4-> R.string.tapGlass
        else -> R.string.lemon


    }

    val description = when(resultado){

        1 -> R.string.desTree
        2-> R.string.lemon
        3-> R.string.glass
        4-> R.string.emptyGlass
        else -> R.string.lemon


    }
    //Se dibuja la fila superior
    TopRow()
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        //Cuando se hace click se comprueba si se está en el paso de exprimir el limón. De ser así
        //se añade un click a la cuenta y se comprueba si el número de clicks que lleva el usuario
        //coincidie con el valor aleatorio de número de clicks para cambiar la imagen. En caso positivo,
        //la imagen cambia.
        //Si se encuenta en cualquier otro paso, se pasa a la siguiente imagen.
        //Si algo falla, se pasa otra vez a la primera imagen
        Button(onClick = {
            if(resultado == 2){
                click ++
                if(click == numeroClicks){
                    resultado ++
                }
            }else if (resultado < 4){
                resultado ++

            }else {
                resultado = 1
            }
                //Establece la forma y color del fondo de la imagen
                         }, shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(Color(red = 196, green = 255, blue = 211))
        ) {//Imagen asignada al botón
            Image(painter = painterResource(id = imageResource), contentDescription = description.toString())

        }
        //Establece un espacio entre imagen y texto de 16 dp
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = stringResource), fontSize = 18.sp)

    }
}

//Dibuja la fila superior
@Composable
fun TopRow(){
    Row (Modifier
        .background(Color(red = 249, green = 228, blue = 76))
        .fillMaxWidth()
        .fillMaxHeight(0.05f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }


}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LemonadeTheme {
        LemonadeApp(modifier = Modifier
            .fillMaxSize())
    }
}