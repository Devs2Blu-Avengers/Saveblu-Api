import sys

def update_properties(url_secret, password_secret, app_url_secret):
    filepath = 'src\\main\\resources\\application-dev.properties'
    
    mapping = {
        "spring.datasource.url=": url_secret,
        "spring.datasource.password=": password_secret,
        "app.url=": app_url_secret
    }
    
    try:
        with open(filepath, 'r') as file:
            content = file.readlines()

        # Updating the content line-by-line
        updated_content = []
        for line in content:
            for key, value in mapping.items():
                if line.startswith(key):
                    line = key + value + "\n"
            updated_content.append(line)
        
        # Writing updated content back to the file
        with open(filepath, 'w') as file:
            file.writelines(updated_content)

        print("Properties updated with the provided secrets.")
    
    except FileNotFoundError:
        print(f"Error: The file '{filepath}' was not found.")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 4:
        print("Usage: python script.py <url_secret> <password_secret> <app_url_secret>")
        sys.exit(1)
    
    url_secret = sys.argv[1]
    password_secret = sys.argv[2]
    app_url_secret = sys.argv[3]
    update_properties(url_secret, password_secret, app_url_secret)
