import styles from "./Logo.module.css";
import { Link } from "react-router-dom";

export function Logo() {
  return (
    <Link to="/">
      <img src="/logo.png" alt="rms logo" className={styles.logo} />
    </Link>
  );
}
