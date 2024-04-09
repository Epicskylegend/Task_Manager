export PGPASSWORD=$POSTGRES_PASSWORD

if psql --no-password -h $POSTGRES_HOST -p $POSTGRES_PORT -U $POSTGRES_USER $POSTGRES_DB -c "select * from Tasks, Categories;"; then
    echo "Task and Category tables already exists."
else
    echo "Creating tables"

  psql --no-password \
      -h $POSTGRES_HOST -p $POSTGRES_PORT \
      -U $POSTGRES_USER $POSTGRES_DB \
      -f /usr/local/bin/Init.sql

fi
