import csv
from faker import Faker
import random

def generate_fake_users(num_users):
    fake = Faker()
    country_codes = ['US', 'GB', 'CA', 'AU', 'DE', 'FR', 'IT', 'ES', 'BR', 'MX', 'JP', 'CN', 'RU', 'IN', 'PK', 'AE', 'SG', 'KR', 'TW', 'DK', 'PL', 'QA']
    language_codes = ['en', 'es', 'fr', 'de', 'it', 'pt', 'zh', 'ja', 'ru', 'ar', 'hi', 'ko', 'uk', 'vi', 'sw', 'he', 'da']

    users = []
    for _ in range(num_users):
        user = {
            'full_name': fake.name(),
            'email': fake.email(),
            'birth_date': fake.date_of_birth(minimum_age=18, maximum_age=90).isoformat(),
            'score': random.randint(0, 100),
            'country_code': random.choice(country_codes),
            'preferred_language': random.choice(language_codes),
            'books_read': random.randint(0, 12)
        }
        users.append(user)

    return users

def write_to_csv(users, filename='fake_users.csv'):
    with open(filename, mode='w', newline='', encoding='utf-8') as file:
        writer = csv.DictWriter(file, fieldnames=users[0].keys())
        writer.writeheader()
        writer.writerows(users)

def main():
    num_users = 1000  # Number of fake users to generate
    fake_users = generate_fake_users(num_users)
    write_to_csv(fake_users)
    print(f"Generated {num_users} fake users and saved to 'fake_users.csv'.")

if __name__ == "__main__":
    main()
