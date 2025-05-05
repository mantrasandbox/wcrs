import styles from "./Logo.module.css";
import { Link } from "react-router-dom";

export function Logo() {
  return <img src="/logo.png" alt="rms logo" className={styles.logo} />;
}
