CREATE TABLE IF NOT EXISTS video (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    youtube_url TEXT NOT NULL,
    duration_minutes INTEGER NOT NULL,
    start_time TEXT NOT NULL
);