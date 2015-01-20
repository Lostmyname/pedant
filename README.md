# pedant

An app that posts a checklist to a newly opened pull request.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Github setup

Add a new webhook for your repo pointing at the `/pull-request` on this app. Select the specific pull requst event to trigger the webhook.

![Setup a webhook on Github poitn at /pull-request](https://dl.dropboxusercontent.com/u/1038218/deletenot/Webhook_-_https___tomc_pagekite_me_pull-request.png "Webhook setup")

## License

MIT
