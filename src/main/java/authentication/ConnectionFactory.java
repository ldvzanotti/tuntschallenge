package authentication;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import static logging.Log.LOGGER;

public class ConnectionFactory {

	private static Credential authorize () throws GeneralSecurityException, IOException {
		LOGGER.fine("Loading authentication file...");
		//Load client secrets
		InputStream in = Sheets.class.getResourceAsStream("/client.json");
		LOGGER.fine("Reading secrets from file...");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				GsonFactory.getDefaultInstance(), new InputStreamReader(in));
		//Level of access to sheet
		List<String> scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS);
		LOGGER.fine("Requesting authorization...");
		// Build flow and trigger user authorization request
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), clientSecrets, scopes)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
				.setAccessType("offline")
				.build();
		LOGGER.fine("Authorization granted.");
		return new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver())
				.authorize("user");
	}

	public static Sheets createSheetsService () throws GeneralSecurityException, IOException {
		LOGGER.fine("Trying to authenticate...");
		Credential credential = authorize();
		LOGGER.fine("Authentication successful.");
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				GsonFactory.getDefaultInstance(), credential)
				.setApplicationName("Google-Sheets-Grades")
				.build();
	}
}
