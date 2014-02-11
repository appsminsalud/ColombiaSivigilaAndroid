package com.ipro.utils.redesSociales;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.vigilatusalud_v3.R;


public class FacebookShareFragment extends Fragment {
	
	MensajeRedesSociales mensaje;
	private static final String TAG = "MainFragment";
	
	private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
    
	private Button shareButton;
	private Button closeButton;
	
	private static final List<String> PERMISSIONS = Arrays.asList("publish_stream");
	
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	
	private boolean pendingPublishReauthorization = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, 
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_facebookshare, container, false);
        
		LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
		authButton.setFragment(this);
		
		shareButton = (Button) view.findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        publishStory();        
		    }
		});
		
		closeButton = (Button) view.findViewById(R.id.btn_cerrar);
		closeButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        getActivity().finish();        
		    }
		});
		
		
		if (savedInstanceState != null) {
			pendingPublishReauthorization = 
				savedInstanceState.getBoolean(PENDING_PUBLISH_KEY, false);
		}
		return view;
	}
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);

    }
	
	
	/*Este metodo se ejecuta apenas el activity padre ha terminado su metodo onCreate, o ha sido instanciado dando lugar
	 * a una forma segura de obtener el parcelable
	*/
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Bundle bundle = this.getArguments();
		if(bundle != null){
			 ParcelMensajeRedesSociales msj = bundle.getParcelable("mensaje");
			 mensaje = msj.getMensajeRedesSociales();
		}
	}

    @Override
    public void onResume() {
        super.onResume();
        
        // For scenarios where the main activity is launched and user
		// session is not null, the session state change notification
		// may not be triggered. Trigger it if it's open/closed.
		Session session = Session.getActiveSession();
		if (session != null &&
				(session.isOpened() || session.isClosed()) ) {
			onSessionStateChange(session, session.getState(), null);
		}
		
        uiHelper.onResume();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
        uiHelper.onSaveInstanceState(outState);
    }
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            shareButton.setVisibility(View.VISIBLE);
            if (pendingPublishReauthorization && 
            		state.equals(SessionState.OPENED_TOKEN_UPDATED)) {
            	pendingPublishReauthorization = false;
            	publishStory();
            }
        } else if (state.isClosed()) {
            shareButton.setVisibility(View.INVISIBLE);
        }
    }

	private void publishStory() {
	    Session session = Session.getActiveSession();
	    if (session != null) {

		    // Check for publish permissions    
		    List<String> permissions = session.getPermissions();
		        if (!isSubsetOf(PERMISSIONS, permissions)) {
		        	pendingPublishReauthorization = true;
		            Session.NewPermissionsRequest newPermissionsRequest = new Session
		                    .NewPermissionsRequest(this, PERMISSIONS);
		            session.requestNewPublishPermissions(newPermissionsRequest);
		            return;
		       }

		    Bundle postParams = new Bundle();
		    postParams.putString("message", mensaje.getMensaje());
		  

		    Request.Callback callback= new Request.Callback() {
		        public void onCompleted(Response response) {
		           
		        	JSONObject graphResponse = null;
		        	if ( !FacebookShareActivity.isInternetAvailable(getActivity().getApplicationContext()))
		        			{
		        		Toast.makeText(getActivity().getApplicationContext(),
		        				"Se necesita activar el acceso a internet para usar esta funcionalidad.",
		        				Toast.LENGTH_LONG).show();
		        			}
		        	else if (response.getError() == null)
			            {
			            graphResponse = response.getGraphObject().getInnerJSONObject();
			            FacebookRequestError error = response.getError();
			            if (error != null) {
			                Toast.makeText(getActivity()
			                     .getApplicationContext(),
			                     error.getErrorMessage(),
			                     Toast.LENGTH_SHORT).show();
			                } else {
			                    Toast.makeText(getActivity()
			                         .getApplicationContext(), 
			                         "el evento se ha publicado con exito!.",
			                         Toast.LENGTH_LONG).show();
			            }
			            
			            
			        }
			         else if (response.getError().getErrorCode() == 506)
			        {
			        	Toast.makeText(getActivity().getApplicationContext(),
			        			"Este evento ya ha sido publicado.",
			        			Toast.LENGTH_LONG).show();
			        }
		            
		        }
		    };

		    Request request = new Request(session, "me/feed", postParams, 
		                          HttpMethod.POST, callback);

		    RequestAsyncTask task = new RequestAsyncTask(request);
		    task.execute();
		}
	}
	
	private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
	    for (String string : subset) {
	        if (!superset.contains(string)) {
	            return false;
	        }
	    }
	    return true;
	}

}
