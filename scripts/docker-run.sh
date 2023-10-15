docker run --name wizard-space_postgres -e POSTGRES_USER=wizard -e POSTGRES_PASSWORD=54321 -d -p 5432:5432 --network wizard-space-infra postgres:alpine

docker run --name wizard-space_app -p 8080:8080 --network wizard-space-infra wizard-space_app