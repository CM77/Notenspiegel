package Notenspiegel;

import java.util.Scanner;
import java.util.*;

public class Notenspiegel {

	private int note;
	private int anzahlschüler;
	private double[] notenliste;
	private double durchschnitt;

	// Noten:
	public double getNote() {
		return note;
	}

	public void setNote(int note) {
		if (note < 1 && note > 6) {
			throw new IllegalArgumentException("Es können nur Schulnoten (1-6) eingegeben werden.");
		}
		this.note = note;
	}

	// Klassenstärke:
	public int getAnzahlSchüler() {
		return anzahlschüler;
	}

	public void setAnzahlSchüler(int anzahlschüler) {
		if (anzahlschüler < 1) {
			throw new IllegalArgumentException("In einer Schulklasse können nicht null Schüler sein.");
		}
		this.anzahlschüler = anzahlschüler;
	}

	// Note-pro-Schüler-Array:
	public double[] getNotenliste() {
		return notenliste;
	}

	public void setNotenliste(double[] notenliste) {
		this.notenliste = notenliste;
	}

	// Durchschnitt
	public double getDurchschnitt() {
		return durchschnitt;
	}

	public void setDurchschnitt(double durchschnitt) {
		this.durchschnitt = durchschnitt;
	}

	public double[] noteBerechnen() {
		Scanner sc2 = new Scanner(System.in);
		notenliste = new double[anzahlschüler];
		System.out.println("");
		System.out.print("Geben Sie nacheinander Schulnoten (1-6) der Klausur/Test/Klassenarbeit ohne Nachkommastellen ein: ");
		System.out.println("");
		System.out.println("");

		OUTER: for (int i = 0; i < notenliste.length; i++) { // for-Schleife fragt Noten in der Höhe der Klassenstärke
																// ab

			System.out.print(i + 1 + ". ");

			boolean error = true;

			while (error) { // while-Schleife sorgt für gültige Eingabe

				int schülernote = sc2.nextInt(); // Abfrage der Schulnoten per Scannerobjekt mit integrierter
													// integer-Validierung

				if (schülernote > 0 && schülernote < 7) {
					note = schülernote;
					notenliste[i] = note;
					continue OUTER;
				} else {
					System.err.println("Bitte eine gültige Schulnote (1-6) eingeben.");
					System.out.println("");
					sc2.close();
				}
			}
		}

		System.out.println("------");
		System.out.println("");
		System.out.print("Notenspiegel der Klasse: ");

		int j = 0;
		double zaehler = 0;
		while (j < notenliste.length) {
			zaehler += notenliste[j];
			j++;
		}
		durchschnitt = Math.round(zaehler / notenliste.length);

		System.out.println(durchschnitt);
		if (durchschnitt > 4.5) {
			System.out.println(
					"Der Durchschnitt ist deutlich zu schlecht und die Klausur bzw. Klassenarbeit muss wiederholt werden.");
		}
		return notenliste;
	}

	public void verteilung() {
		Map<Double, Integer> notenzählen = new HashMap<Double, Integer>();

		for (double i : notenliste) {
			if (!notenzählen.containsKey(i))
				notenzählen.put(i, 1);

			else {
				notenzählen.put(i, notenzählen.get(i) + 1);
			}
		}

		System.out.println("");
		System.out.println("Die Häufigkeiten der Noten lauten:");

		if (notenzählen.containsKey(1.0)) {
			int a = notenzählen.get(1.0);
			System.out.println("Die Note 1 kam " + a + " mal vor.");
		}  if (notenzählen.containsKey(2.0)) {
			int b = notenzählen.get(2.0);
			System.out.println("Die Note 2 kam " + b + " mal vor.");
		}  if (notenzählen.containsKey(3.0)) {
			int c = notenzählen.get(3.0);
			System.out.println("Die Note 3 kam " + c + " mal vor.");
		}  if (notenzählen.containsKey(4.0)) {
			int d = notenzählen.get(4.0);
			System.out.println("Die Note 4 kam " + d + " mal vor.");
		}  if (notenzählen.containsKey(5.0)) {
			int e = notenzählen.get(5.0);
			System.out.println("Die Note 5 kam " + e + " mal vor.");
		}  if (notenzählen.containsKey(6.0)) {
			int f = notenzählen.get(6.0);
			System.out.println("Die Note 6 kam " + f + " mal vor.");
		} 
	}

	public int klassenGrößeEingabe() {
		Scanner sc = new Scanner(System.in);

		// Klassenstärke - kann nicht 0 oder über 40 sein

		boolean error = true;

		while (error) {
			System.out.print("Geben Sie hier die Anzahl Schüler in der Klasse ein: ");
			int schüler = sc.nextInt();

			if (schüler > 0 && schüler < 40) {
				anzahlschüler = schüler;
				error = false;
			} else {
				System.out.println("");
				System.err.println("Bitte geben Sie eine gültige Anzahl Schüler ein.");
				System.out.println("");
			}
		}

		return anzahlschüler;
	}

	public static void main(String[] args) {
		System.out.println("Notenspiegel - Schnell sehen, wie die Klasse abgeschnitten hat.");
		System.out.println("---------------------------------------------------------------");
		System.out.println("");
		Notenspiegel notenspiegel = new Notenspiegel();
		notenspiegel.klassenGrößeEingabe();
		notenspiegel.noteBerechnen();
		notenspiegel.verteilung();
	}
}
