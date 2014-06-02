package com.reproductor.Listar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

import android.content.Intent;
import android.net.Uri;




import com.reproductor.FileIO.FileIO;

public class Listar {
	
	
	
	public Queue<Uri> listado = new LinkedList<Uri>(); ;
	
		public  Queue<Uri> load(FileIO files) {// inntenta cargar un archivo llamdo
											// ormigas dentro del direcctorio
											// assets
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.leerArchivo(".cancione")));
			// lee las entradas del archivo linea a linea
			// si el almacenamiento o en archivo no esta diponible se bolveran
			// los valores por defecto		
			
			
			String sCadena="";			 
			while ((sCadena = in.readLine())!=null) {
			  listado.add(android.net.Uri.parse(sCadena));
			}
			
		
			
			
		} catch (IOException e) {
			// TODO: handle exception
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				// TODO: handle exception
			}

		}
		return listado;
	}

	public  void save(FileIO files,Object[] array) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.escribirArchivo(".cancione")));			
			//out.write("\n");
			for (int i = 0; i < array.length; i++) {
				out.write((((Uri) array[i]).toString()));
				out.write("\n");
			}

		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e2) {
				// TODO: handle exception
			}
		}

	}
	
	public static Intent cargarArchivos(){
		Intent eligeMusica = new Intent(Intent.ACTION_PICK/*
				 * variable
				 * constante que nso
				 * va a cevolber los
				 * datos
				 * seleccionados
				 */,
				 android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
		
		 return eligeMusica;
		
	}
	
	
	
	

}
