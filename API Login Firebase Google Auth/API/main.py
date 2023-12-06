import logging
import os

from flask import Flask, jsonify, request
import flask_cors
from google.appengine.ext import ndb
import google.auth.transport.requests
import google.oauth2.id_token
import requests_toolbelt.adapters.appengine

requests_toolbelt.adapters.appengine.monkeypatch()
HTTP_REQUEST = google.auth.transport.requests.Request()

app = Flask(__name__)
flask_cors.CORS(app)

@app.route("/notes", methods=["GET"])
def list_notes():

    # mulai autentikasi firebase auth
    id_token = request.headers["Authorization"].split(" ").pop()
    claims = google.oauth2.id_token.verify_firebase_token(
        id_token, HTTP_REQUEST, audience=os.environ.get("GOOGLE_CLOUD_PROJECT")
    )
    if not claims:
        return "Unauthorized", 401

    notes = query_database(claims["sub"])

    return jsonify(notes)


@app.route("/notes", methods=["POST", "PUT"])
def add_note():

    # autentikasi firebase auth
    id_token = request.headers["Authorization"].split(" ").pop()
    claims = google.oauth2.id_token.verify_firebase_token(
        id_token, HTTP_REQUEST, audience=os.environ.get("GOOGLE_CLOUD_PROJECT")
    )
    if not claims:
        return "Unauthorized", 401

    # # [START gae_python_create_entity]
    # data = request.get_json()

    # # Populates note properties according to the model,
    # # with the user ID as the key name.
    # note = Note(parent=ndb.Key(Note, claims["sub"]), message=data["message"])

    # # Some providers do not provide one of these so either can be used.
    # note.friendly_id = claims.get("name", claims.get("email", "Unknown"))
    # # [END gae_python_create_entity]

    # # Stores note in database.
    # note.put()

    return "OK", 200


@app.errorhandler(500)
def server_error(e):
    # Log the error and stacktrace.
    logging.exception("An error occurred during a request.")
    return "An internal error occurred.", 500
