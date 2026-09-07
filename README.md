# FeatherQuest

FeatherQuest helps birders plan an excursion from a starting address. It uses
recent eBird observations and Google Maps to find nearby target species and
generate an efficient route.

## Live site

[Open FeatherQuest](https://vedikas729.github.io/FeatherQuest/)

## Repository structure

- `src/` - React frontend
- `public/` - static frontend assets
- `backend/birdingexcursion/` - Java Lambda source and Maven project
- `backend/events/` - local invocation events
- `backend/template.yaml` - AWS SAM infrastructure configuration

The deployed frontend calls the AWS API Gateway endpoints for the backend. The
backend repository is included here for maintenance and future deployments.

## Frontend development

Requirements: Node.js and npm.

```bash
npm ci
npm start
```

The development server runs at `http://localhost:3000`.

The frontend expects `REACT_APP_API_KEY` when it is built. This is the API
Gateway key used by the browser; configure it through your local environment or
your GitHub Pages build environment, and never commit a real value.

Run the frontend tests and production build with:

```bash
npm test -- --watchAll=false
npm run build
```

## Backend development

Requirements: Java, Maven, and AWS SAM CLI for deployment or local Lambda
emulation.

The backend API client reads these variables at runtime:

```bash
export EBIRD_API_KEY="your-ebird-api-key"
export GOOGLEPLATFORM_API_KEY="your-google-maps-api-key"
```

Do not commit these values. Configure them through AWS Lambda environment
settings or a secrets manager for deployed functions.

Run the backend tests from its Maven project directory:

```bash
cd backend/birdingexcursion
mvn test
```

Generated Maven and SAM output is intentionally ignored by Git.

## Deployment

The frontend can be deployed using the existing npm scripts:

```bash
npm run deploy
```

AWS deployment configuration is in `backend/template.yaml` and
`backend/samconfig.toml`. Review the Lambda function names, handlers, API
routes, and environment configuration before using `sam build` or `sam deploy`.

Never commit API keys, private keys, `.env` files, Maven output, or SAM build
artifacts.
