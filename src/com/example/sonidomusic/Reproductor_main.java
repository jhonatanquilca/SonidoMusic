package com.example.sonidomusic;

import java.io.IOException;

import com.reproductor.Listar.Listar;

import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class Reproductor_main extends Activity implements OnClickListener {

	Button btnPlay, btnDetener, btnSiguiente, btnAnterior, btnListar;
	SoundPool soundPool;
	int idSonido = -1;
	Uri[] sonidosVec = new Uri[] {};
	Listar agregarCancione = new Listar();
	int numCancion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reproductor_main);

		btnPlay = (Button) this.findViewById(R.id.PlayPauseButton);
		btnDetener = (Button) this.findViewById(R.id.DetenerButton);
		btnSiguiente = (Button) this.findViewById(R.id.SiguienteButton);
		btnAnterior = (Button) this.findViewById(R.id.AnteriorButton);
		btnListar = (Button) this.findViewById(R.id.listarButtom);

		btnPlay.setOnClickListener(this);
		btnDetener.setOnClickListener(this);
		btnSiguiente.setOnClickListener(this);
		btnAnterior.setOnClickListener(this);
		btnListar.setOnClickListener(this);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);// controles de sonido
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reproductor_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnPlay) {
			if (btnPlay.getText().equals("Reproducir ►")) {

				play(numCancion);

			} else {
				// pause();
			}

		} else if (v == btnDetener) {

		} else if (v == btnSiguiente) {

		} else if (v == btnAnterior) {

		} else if (v == btnListar) {
			Intent t = agregarCancione.cargarArchivos();
			startActivityForResult(t, 0);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			
			sonidosVecAgreagr(data.getData());

		}

	}

	// ----------- Mis Metodos----------
	private void play(int numCancion) {
		btnPlay.setText("Pause ||");

		cargarSonido(numCancion);

		if (idSonido != -1) {

			int loop = 0;
			if (sonidosVec.length > 1) {
				loop = -1;

			}

			soundPool.play(idSonido/* ide de sonido */, 1/* volumen izquiedo */,
							1/* volumen derecho */, 0/* prioridad */,
							loop/* loop */, 1/* velocidad de reproduccion */);

		}

		mensajes("Se esta reproduciendo", 20);

	}

	private void pause() {
		btnPlay.setText("Reproducir ►");

	}

	private void siguiente() {

	}

	private void anterior() {

	}

	private void cargarSonido(int id) {

		try {

			idSonido = soundPool.load(sonidosVec[id].toString(), 1);// lansa una
																	// ecepcion
																	// si
			// esque no se carga

		} catch (Exception e) {
			// TODO: handle exception
			mensajes("El sonido no se ha cargado correctamente", 10);

		}

	}

	private void sonidosVecAgreagr(Uri direccion) {
		Uri[] aux = new Uri[sonidosVec.length];
		for (int i = 0; i < sonidosVec.length; i++) {
			aux[i] = sonidosVec[i];
		}

		sonidosVec = new Uri[aux.length + 1];

		for (int i = 0; i < aux.length; i++) {
			sonidosVec[i] = aux[i];
		}

		sonidosVec[aux.length] = direccion;

	}

	private void mensajes(String mensaje, int duracion) {
		Toast t = Toast.makeText(this, mensaje, Toast.LENGTH_LONG + duracion);
		t.show();
	}

}
